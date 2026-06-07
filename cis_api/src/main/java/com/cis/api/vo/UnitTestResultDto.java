package com.cis.api.vo;



import java.time.LocalDate;

import lombok.Data;
@Data
public class UnitTestResultDto {

    private Integer id;
    private String bugId;
    private String bugDesc;
    private String stepsToReproduce;

    private String severity;
    private String priority;

    private String moduleName;

    private String types;
    private String status;

    private String bugType;

    private String fileName;

    private LocalDate updatedOn;

    private String taskUserName;
    private String effortTaken;
    private String percentageCompleted;
    private String schDate;

    private String taskStatus;

    // Getters & Setters
}

