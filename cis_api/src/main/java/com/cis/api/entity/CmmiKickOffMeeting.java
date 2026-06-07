package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cmmi_kick_off_meeting", schema = "pts", catalog = "pts")
@Data
public class CmmiKickOffMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cmmi_Project_Id", nullable = false)
    private Integer cmmiProjectId;

    @Column(name = "meeting_Date")
    private LocalDate meetingDate;

    @Column(name = "meeting_Venue", length = 200)
    private String meetingVenue;

    @Column(name = "client_Participants", length = 1000)
    private String clientParticipants;

    @Column(name = "additional_Information", length = 500)
    private String additionalInformation;

    @Column(name = "attachment_Name", length = 300)
    private String attachmentName;

    @Column(name = "verified_By")
    private Integer verifiedBy;

    @Column(name = "verified_On")
    private LocalDate verifiedOn;
}
