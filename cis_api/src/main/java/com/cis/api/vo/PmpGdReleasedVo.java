package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class PmpGdReleasedVo {
    private Long gdReleasedId;
    private Long pmpGdId;
    private String type;
    private Double version;
    private String releasedActiveStatus;
    private Long torVerId;
    private UserSetupVo approvedUser;
    private UserSetupVo submittedUser;
    private UserSetupVo releasedUser;
    private Date submittedDate;
    private Date approvedDate;
    private Date releasedDate;
    private String approvedStatus;
    private String submittedStatus;
    private UserSetupVo createdUser;
    private String createdDate;
    private String clientViewStatus;
    private Integer projectId;
    private Integer groupId;
    private String reviseStatus;
    private String reasonForRevise;
}
