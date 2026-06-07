package com.cis.api.vo;

import lombok.Data;

import java.sql.Date;

import com.cis.api.entity.SrsVersionHistory;

@Data
public class SrsPointSprintVo {

    private Integer srsVerHisId;
    private String srsNo;
    private String srsPoint;
    private String crsNo;
    private String moduleName;
    private String remarks;
    private Double plannedEffort;
    private java.util.Date plannedEndDate;
    private java.util.Date plannedStartDate;


    // Optional sprint fields (uncomment if needed)
    private Integer taskId;
    private String owner;

    public SrsPointSprintVo(SrsVersionHistory entity) {

        this.srsVerHisId = entity.getSrsVerHisId();

        if (entity.getSrsDetails() != null) {
            this.srsNo = entity.getSrsDetails().getSrsNo();
            this.srsPoint = entity.getSrsPoint();

            if (entity.getSrsDetails().getCrs() != null) {
                this.crsNo = entity.getSrsDetails().getCrs().getCrsNo();
            }

            if (entity.getSrsDetails().getModule() != null) {     
                this.moduleName = entity.getSrsDetails().getModule().getModuleName();
            }
            this.plannedStartDate = entity.getSrsDetails().getPlannedStartDate(); 
            this.plannedStartDate = entity.getSrsDetails().getPlannedEndDate();
            this.plannedEffort = entity.getSrsDetails().getPlannedEffort();
            this.remarks = entity.getSrsDetails().getSrsRemarks();

            // Optional mapping: Only if task exists
            if (entity.getSrsDetails().getTask() != null) {                 
                this.taskId = entity.getSrsDetails().getTask().getTAId();
                if (entity.getSrsDetails().getTask().getUser() != null) {
                    this.owner = entity.getSrsDetails().getTask().getUser().getUserName();
                }
            }
        }
    }
}
