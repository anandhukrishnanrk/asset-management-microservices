package com.cis.api.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CisQueriesVo {

    private Integer id;
    private Integer projectId;
    private Integer moduleId;
    private String message;
    private String tabCode;
    private Integer linkId;
    private Integer userId;
    private String attachmentPath;
    private String status;
    private Date updatedOn;
    private String activeStatus;

}
