package com.cis.api.vo;

import java.util.List;

import com.cis.api.entity.BugType;
import com.cis.api.entity.ModuleSetup;
import com.cis.api.entity.SeatUserAlloted;
import com.cis.api.entity.TestReportResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitTestResponseDto {

    private Integer id;                // generated number
    private String bugId;
    private String bugDesc;
    private String stepsToReproduce;
    private String severity;
    private String priority;

    private String moduleName;

    private String type;               // or "types"
    private String status;

    private String bugType;

    private String fileName;           // TestReportUpload.file_Name

    private String updatedOn;

    private List<UserTaskDto> tasks;
    private String taskStatus; 
    private String User;
    // overall status
}

