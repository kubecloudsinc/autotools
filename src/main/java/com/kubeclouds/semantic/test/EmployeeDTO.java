package com.kubeclouds.semantic.test;

import com.kubeclouds.domain.appdb.Department;
import com.kubeclouds.domain.appdb.Employee;
import com.kubeclouds.domain.appdb.Job;
import com.kubeclouds.domain.appdb.JobHistory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private BigDecimal salary;

    private Employee manager;

    private Department department;

    private Job job;

    private Set<JobHistory> jobHistory=new HashSet<JobHistory>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Set<JobHistory> getJobHistory() {
        return jobHistory;
    }

    public void setJobHistory(Set<JobHistory> jobHistory) {
        this.jobHistory = jobHistory;
    }

    public void transfromFromObject(Employee employee){
        this.id=employee.getId();
        this.firstName=employee.getFirstName();
        this.lastName=employee.getLastName();
        this.phoneNumber=employee.getPhoneNumber();
        this.email=employee.getEmail();
        this.salary=employee.getSalary();
        this.manager=employee.getManager();
        this.department=employee.getDepartment();
        this.job=employee.getJob();
        this.jobHistory=employee.getJobHistory();

    }
}
