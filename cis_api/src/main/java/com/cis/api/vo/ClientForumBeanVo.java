package com.cis.api.vo;

import lombok.Data;

@Data
public class ClientForumBeanVo {
    private Integer forumId;
    private ClientForumSubjectBeanVo subjectIDForum;
    private ClientVo forumAddedBy;
    private String forum;
    private String forumDescription;
    private String forumDate;
}
