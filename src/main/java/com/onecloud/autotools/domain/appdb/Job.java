
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "JOBS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job{

    private static final long serialVersionUID = 3194469267760500809L;

    private String jobId;

    private String title;

    @Id
    @Column(name="JOB_ID", length = 10, nullable = false, columnDefinition = "char")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Size(max = 35)
    @Column(name="JOB_TITLE", length = 35, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getTitle() == null) {
            return false;
        } else if (o instanceof Job) {
            Job that = (Job) o;
            return this.getTitle().equals(that.getTitle());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getTitle() == null ? System.identityHashCode(this) : 17 * this.getTitle()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}