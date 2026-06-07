package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class SupportDashboardVo {

    private Integer projectId;
    private String projectName;

    // Total counts by ticket type
    private long countAdditionModification;
    private long countBug;
    private long countDataCorrection;
    private long countDoubtClearance;
    private long countComplaints;
    private long countAdmin;

    // Closed counts by ticket type
    private long closedCountAdditionModification;
    private long closedCountBug;
    private long closedCountDataCorrection;
    private long closedCountDoubtClearance;
    private long closedCountComplaints;
    private long closedCountAdmin;

    // Open counts by ticket type
    private long openCountAdditionModification;
    private long openCountBug;
    private long openCountDataCorrection;
    private long openCountDoubtClearance;
    private long openCountComplaints;
    private long openCountAdmin;

    // Grand totals
    private long totalCount;
    private long totalClosed;
    private long totalOpen;

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public long getCountAdditionModification() { return countAdditionModification; }
    public void setCountAdditionModification(long countAdditionModification) { this.countAdditionModification = countAdditionModification; }

    public long getCountBug() { return countBug; }
    public void setCountBug(long countBug) { this.countBug = countBug; }

    public long getCountDataCorrection() { return countDataCorrection; }
    public void setCountDataCorrection(long countDataCorrection) { this.countDataCorrection = countDataCorrection; }

    public long getCountDoubtClearance() { return countDoubtClearance; }
    public void setCountDoubtClearance(long countDoubtClearance) { this.countDoubtClearance = countDoubtClearance; }

    public long getCountComplaints() { return countComplaints; }
    public void setCountComplaints(long countComplaints) { this.countComplaints = countComplaints; }

    public long getCountAdmin() { return countAdmin; }
    public void setCountAdmin(long countAdmin) { this.countAdmin = countAdmin; }

    public long getClosedCountAdditionModification() { return closedCountAdditionModification; }
    public void setClosedCountAdditionModification(long v) { this.closedCountAdditionModification = v; }

    public long getClosedCountBug() { return closedCountBug; }
    public void setClosedCountBug(long closedCountBug) { this.closedCountBug = closedCountBug; }

    public long getClosedCountDataCorrection() { return closedCountDataCorrection; }
    public void setClosedCountDataCorrection(long v) { this.closedCountDataCorrection = v; }

    public long getClosedCountDoubtClearance() { return closedCountDoubtClearance; }
    public void setClosedCountDoubtClearance(long v) { this.closedCountDoubtClearance = v; }

    public long getClosedCountComplaints() { return closedCountComplaints; }
    public void setClosedCountComplaints(long closedCountComplaints) { this.closedCountComplaints = closedCountComplaints; }

    public long getClosedCountAdmin() { return closedCountAdmin; }
    public void setClosedCountAdmin(long closedCountAdmin) { this.closedCountAdmin = closedCountAdmin; }

    public long getOpenCountAdditionModification() { return openCountAdditionModification; }
    public void setOpenCountAdditionModification(long v) { this.openCountAdditionModification = v; }

    public long getOpenCountBug() { return openCountBug; }
    public void setOpenCountBug(long openCountBug) { this.openCountBug = openCountBug; }

    public long getOpenCountDataCorrection() { return openCountDataCorrection; }
    public void setOpenCountDataCorrection(long v) { this.openCountDataCorrection = v; }

    public long getOpenCountDoubtClearance() { return openCountDoubtClearance; }
    public void setOpenCountDoubtClearance(long v) { this.openCountDoubtClearance = v; }

    public long getOpenCountComplaints() { return openCountComplaints; }
    public void setOpenCountComplaints(long openCountComplaints) { this.openCountComplaints = openCountComplaints; }

    public long getOpenCountAdmin() { return openCountAdmin; }
    public void setOpenCountAdmin(long openCountAdmin) { this.openCountAdmin = openCountAdmin; }

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long totalCount) { this.totalCount = totalCount; }

    public long getTotalClosed() { return totalClosed; }
    public void setTotalClosed(long totalClosed) { this.totalClosed = totalClosed; }

    public long getTotalOpen() { return totalOpen; }
    public void setTotalOpen(long totalOpen) { this.totalOpen = totalOpen; }
}