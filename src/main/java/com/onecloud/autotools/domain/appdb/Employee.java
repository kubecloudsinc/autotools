
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "EMPLOYEE_ID"))
public class Employee extends IdentifiableEntity implements Comparable<Employee>{

    private static final long serialVersionUID = 3193569267760500809L;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private BigDecimal salary;

    private Employee manager;

    private Department department;

    private Job job;

    private Set<JobHistory> jobHistory=new HashSet<JobHistory>();

    @Size(max = 20)
    @NotEmpty
    @NotNull
    @Column(name="FIRST_NAME", length = 20, nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(max = 25)
    @NotEmpty
    @NotNull
    @Column(name="LAST_NAME", length = 25, nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @Size(max = 25)
    @NotEmpty
    @NotNull
    @Column(name="EMAIL", length = 25, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(max = 20)
    @Column(name="PHONE_NUMBER", length = 20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="SALARY")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @OneToOne
    @JoinColumn(name = "MANAGER_ID")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToOne
    @JoinColumn(name = "JOB_ID")
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @OneToMany(mappedBy="employee", targetEntity=JobHistory.class, fetch = FetchType.LAZY)
    public Set<JobHistory> getJobHistory() {
        return jobHistory;
    }

    public void setJobHistory(Set<JobHistory> jobHistory) {
        this.jobHistory = jobHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Employee) {
            Employee that = (Employee) o;
            return this.getId().equals(that.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getEmail() == null ? System.identityHashCode(this) : 17 * this.getEmail()
                .hashCode();
    }

    @Transient
    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(Employee o) {

        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return -1;
        } else if (o instanceof Employee) {
            Employee that = (Employee) o;
            return (int) (this.getId().longValue()-that.getId().longValue());
        }
        return -1;
    }
}