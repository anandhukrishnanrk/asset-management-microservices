package com.cis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RtmRowDto {
    private String moduleName;

    private String crsNo;
    private String crsDetails;
    private String dependentRequirement;

    private String srsNo;
    private String srsDetails;
    private String dependentSrs;

    private String sddActivity;
    private String sddEntryNo;

    private String testCaseNo;
    private String testCaseDetails;
}