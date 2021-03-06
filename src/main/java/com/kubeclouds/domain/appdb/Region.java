
package com.kubeclouds.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REGIONS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "REGION_ID"))
public class Region extends IdentifiableEntity {

    private static final long serialVersionUID = 2993569267760500809L;

    private String regionName;

    private Set<Country> countries=new HashSet<Country>();

    @Size(max = 25)
    @Column(name="REGION_NAME", length = 20, nullable = false)
    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @OneToMany(mappedBy="region", targetEntity=Country.class, fetch = FetchType.LAZY)
    public Set<Country> getCountries() {

        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getRegionName() == null) {
            return false;
        } else if (o instanceof Region) {
            Region that = (Region) o;
            return this.getRegionName().equals(that.getRegionName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getRegionName() == null ? System.identityHashCode(this) : 17 * this.getRegionName()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getRegionName();
    }
}