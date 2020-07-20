
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COUNTRIES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country {

    private static final long serialVersionUID = 2993569267760500809L;

    private String countryId;
    private String countryName;
    private Region region;
    private Set<Location> locations=new HashSet<Location>();

    @Id
    @Column(name="COUNTRY_ID", length = 2, nullable = false, columnDefinition = "char")
    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Size(max = 40)
    @Column(name="COUNTRY_NAME", length = 40, nullable = false)
    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @ManyToOne
    @JoinColumn(name="REGION_ID")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(mappedBy="country", targetEntity=Location.class, fetch = FetchType.LAZY)
    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getCountryName() == null) {
            return false;
        } else if (o instanceof Country) {
            Country that = (Country) o;
            return this.getCountryName().equals(that.getCountryName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getCountryName() == null ? System.identityHashCode(this) : 17 * this.getCountryName()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getCountryName();
    }
}