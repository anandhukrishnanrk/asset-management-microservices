package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CmmiProjectVo {
    private Long id;
    private ProjectDetailsVo project;
    private LocalDate addedOn;
    private Integer addedBy;
    private String dashboardStatus;
    private String email;
    private String emailProvider;
    private String password;
    private String mobile;
}
