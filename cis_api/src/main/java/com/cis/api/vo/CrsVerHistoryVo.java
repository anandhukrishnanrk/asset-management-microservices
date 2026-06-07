package com.cis.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CrsVerHistoryVo {

    private int crsVerHisId;

    private CrsVersionVo crsVersion;  
    private CrsVo crs;    

    private String status;
    private String date;
    private String action;
    private String doneBy;

    private String crsPoint;
    private String crsRefNo;
    private String moduleName;
    private String crsNo;

    private String type;
}
