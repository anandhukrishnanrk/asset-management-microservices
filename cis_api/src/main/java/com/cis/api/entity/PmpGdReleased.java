package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "pmp_gd_released", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PmpGdReleased {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gd_released_Id")
    private Long gdReleasedId;

    @Column(name = "pmp_Gd_Id")
    private Long pmpGdId;

    @Column(name = "type", length = 5)
    private String type;

    @Column(name = "version")
    private Double version;

    @Column(name = "released_Active_Status", length = 1)
    private String releasedActiveStatus;

    @Column(name = "tor_ver_Id")
    private Long torVerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_User")
    private UserSetup approvedUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submitted_User")
    private UserSetup submittedUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "released_User")
    private UserSetup releasedUser;

    @Column(name = "submitted_Date")
    private Date submittedDate;

    @Column(name = "approved_Date")
    private Date approvedDate;

    @Column(name = "released_Date")
    private Date releasedDate;

    @Column(name = "approved_Status", length = 2)
    private String approvedStatus;

    @Column(name = "submitted_Status", length = 2)
    private String submittedStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_User")
    private UserSetup createdUser;

    @Column(name = "created_Date", length = 20)
    private String createdDate;

    @Column(name = "client_View_Status", columnDefinition = "TEXT")
    private String clientViewStatus;

    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "group_Id")
    private Integer groupId;

    @Column(name = "revisestatus", length = 10)
    private String reviseStatus;

    @Column(name = "reason_For_Revise", length = 500)
    private String reasonForRevise;
}
