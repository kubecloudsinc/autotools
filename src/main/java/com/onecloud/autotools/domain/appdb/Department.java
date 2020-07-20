
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENTS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "DEPARTMENT_ID"))
public class Department extends IdentifiableEntity {

    private static final long serialVersionUID = 3993569267760500809L;

    private String departmentName;

    private Location location;

    @Size(max = 30)
    @Column(name="DEPARTMENT_NAME", length = 30)
    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    @ManyToOne
    @JoinColumn(name="LOCATION_ID")
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getDepartmentName() == null) {
            return false;
        } else if (o instanceof Department) {
            Department that = (Department) o;
            return this.getDepartmentName().equals(that.getDepartmentName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getDepartmentName() == null ? System.identityHashCode(this) : 17 * this.getDepartmentName()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getDepartmentName();
    }
}