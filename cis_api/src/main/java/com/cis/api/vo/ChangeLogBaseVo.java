package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ChangeLogBaseVo {
    private Long verId;
    private ProjectDetailsVo project;
    private String baseVersion;
    private String requestFrom;
    private String requestDate;
    private String releaseType;
    private String status;
    private String description;
    private Integer approvedBy;
    private LocalDate approvedOn;
    private String approvedByClient;
}
