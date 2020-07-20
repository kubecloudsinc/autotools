
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LOCATIONS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "LOCATION_ID"))
public class Location extends IdentifiableEntity {

    private static final long serialVersionUID = 2993569267760500809L;

    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateOrProvince;

    private Country country;

    private Set<Department> departments = new HashSet<Department>();

    @Size(max = 40)
    @Column(name="STREET_ADDRESS", length = 40)
    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Size(max = 12)
    @Column(name="POSTAL_CODE", length = 12)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Size(max = 30)
    @NotEmpty
    @NotNull
    @Column(name="CITY", length = 30,nullable = false)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Size(max = 25)
    @Column(name="STATE_PROVINCE", length = 25)
    public String getStateOrProvince() {
        return this.stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy="location", targetEntity=Department.class, fetch = FetchType.LAZY)
    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getStreetAddress() == null) {
            return false;
        } else if (o instanceof Location) {
            Location that = (Location) o;
            return this.getStreetAddress().equals(that.getStreetAddress());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getStreetAddress() == null ? System.identityHashCode(this) : 17 * this.getStreetAddress()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getStreetAddress();
    }
}
