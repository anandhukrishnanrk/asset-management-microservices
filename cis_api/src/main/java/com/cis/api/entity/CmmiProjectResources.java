package com.cis.api.entity;



import jakarta.persistence.*;
import java.util.Date;



@Entity
@Table(name = "cmmi_project_resources", schema = "pts", catalog = "pts")
public class CmmiProjectResources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "team_Meeting_Id")
    private Integer teamMeetingId;

    @Column(name = "pmp_Id")
    private Integer pmpId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_User_Id")
    private SeatUserAlloted seatUserId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_Role")
//    private Rolesetup userRole;

    @Temporal(TemporalType.DATE)
    @Column(name = "from_Date")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_Date")
    private Date toDate;

    @Column(name = "training_Need")
    private String trainingNeed;

    @Column(name = "training_Need_Remarks")
    private String trainingNeedRemarks;

    @Column(name = "training_Status")
    private String trainingStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "trained_On")
    private Date trainedOn;

    @Column(name = "training_Type")
    private String trainingType;

    @Column(name = "responsibility")
    private String responsibility;

    @Column(name = "TNI_Update_Remarks")
    private String TNIUpdateRemarks;

    @Column(name = "trained_By")
    private String trainedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allocation_Id")
    private ResourceAllocation resourceAllocation;

    @Column(name = "skill_Index")
    private Double skillIndex;

    // =========================
    // Constructors
    // =========================

    public CmmiProjectResources() {
    }

    // =========================
    // Getters and Setters
    // =========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamMeetingId() {
        return teamMeetingId;
    }

    public void setTeamMeetingId(Integer teamMeetingId) {
        this.teamMeetingId = teamMeetingId;
    }

    public Integer getPmpId() {
        return pmpId;
    }

    public void setPmpId(Integer pmpId) {
        this.pmpId = pmpId;
    }

    public SeatUserAlloted getSeatUserId() {
        return seatUserId;
    }

    public void setSeatUserId(SeatUserAlloted seatUserId) {
        this.seatUserId = seatUserId;
    }

//    public Rolesetup getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(Rolesetup userRole) {
//        this.userRole = userRole;
//    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getTrainingNeed() {
        return trainingNeed;
    }

    public void setTrainingNeed(String trainingNeed) {
        this.trainingNeed = trainingNeed;
    }

    public String getTrainingNeedRemarks() {
        return trainingNeedRemarks;
    }

    public void setTrainingNeedRemarks(String trainingNeedRemarks) {
        this.trainingNeedRemarks = trainingNeedRemarks;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public Date getTrainedOn() {
        return trainedOn;
    }

    public void setTrainedOn(Date trainedOn) {
        this.trainedOn = trainedOn;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getTNIUpdateRemarks() {
        return TNIUpdateRemarks;
    }

    public void setTNIUpdateRemarks(String TNIUpdateRemarks) {
        this.TNIUpdateRemarks = TNIUpdateRemarks;
    }

    public String getTrainedBy() {
        return trainedBy;
    }

    public void setTrainedBy(String trainedBy) {
        this.trainedBy = trainedBy;
    }

    public ResourceAllocation getResourceAllocation() {
        return resourceAllocation;
    }

    public void setResourceAllocation(ResourceAllocation resourceAllocation) {
        this.resourceAllocation = resourceAllocation;
    }

    public Double getSkillIndex() {
        return skillIndex;
    }

    public void setSkillIndex(Double skillIndex) {
        this.skillIndex = skillIndex;
    }
}
