package com.cis.api.vo;

import lombok.Data;
import java.util.Date;

@Data
public class SrsVersionHistoryVo {
    private Integer srsVerHisId;
    private Integer srsId;
    private Integer srsVersionId;
    private Integer prototypeId;
    private String status;
    private String srsPoint;
    private String srsRefNo;
    private String requestStatus;
    private Date deletedOn;
    private String deleteRemarks;
    private Date addedOn;
    
    private String srsNo;          // srsDetails.srsNo
    private String crsNo;          // srsDetails.crs.crsNo
    private String moduleName;     // srsDetails.module.moduleName
    private String srsRemarks; 
    
    public SrsVersionHistoryVo(Integer srsVerHisId, String srsNo, String srsPoint,
            String crsNo, String moduleName, String srsRemarks) {
this.srsVerHisId = srsVerHisId;
this.srsNo = srsNo;
this.srsPoint = srsPoint;
this.crsNo = crsNo;
this.moduleName = moduleName;
this.srsRemarks = srsRemarks;
}
}

