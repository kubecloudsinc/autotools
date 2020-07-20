package com.onecloud.autotools.domain.appdb;

import java.io.Serializable;
import java.sql.Date;

public class JobHistoryId implements Serializable {

    private Long employeeId;

    private Date startDate;

    public JobHistoryId() {
    }

    public JobHistoryId(Long employeeId, Date startDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
