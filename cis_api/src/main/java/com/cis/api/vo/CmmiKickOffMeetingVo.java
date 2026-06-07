package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CmmiKickOffMeetingVo {
    private Integer id;
    private Integer cmmiProjectId;
    private LocalDate meetingDate;
    private String meetingVenue;
    private String clientParticipants;
    private String additionalInformation;
    private String attachmentName;
    private Integer verifiedBy;
    private LocalDate verifiedOn;
}
