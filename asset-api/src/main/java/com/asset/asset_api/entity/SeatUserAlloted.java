package com.asset.asset_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "seat_user_alloted", schema = "kranmaster" ,catalog = "kranmaster")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeatUserAlloted implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allot_Id")
    private Integer allotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_Id", nullable = false)
    private HierarchySetup seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", nullable = false)
    private UserSetup user;

    @Column(name = "active_Date")
    private LocalDateTime activeDate;

    @Column(name = "deactive_Date")
    private LocalDateTime deactiveDate;

    @Column(name = "active_Status", length = 1, nullable = false)
    private String activeStatus = "Y";

    @Column(name = "passwd", nullable = false)
    private String passwd;

    @Column(name = "password_Reset", nullable = false)
    private String passwdReset;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "password_Resetted_On", nullable = false)
    private LocalDate passwordResetOn;

    public SeatUserAlloted() {}

    public Integer getAllotId() {
        return allotId;
    }

    public void setAllotId(Integer allotId) {
        this.allotId = allotId;
    }

    public HierarchySetup getSeat() {
        return seat;
    }

    public void setSeat(HierarchySetup seat) {
        this.seat = seat;
    }

    public UserSetup getUser() {
        return user;
    }

    public void setUser(UserSetup user) {
        this.user = user;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public LocalDateTime getDeactiveDate() {
        return deactiveDate;
    }

    public void setDeactiveDate(LocalDateTime deactiveDate) {
        this.deactiveDate = deactiveDate;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswdReset() {
        return passwdReset;
    }

    public void setPasswdReset(String passwdReset) {
        this.passwdReset = passwdReset;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getPasswordResetOn() {
        return passwordResetOn;
    }

    public void setPasswordResetOn(LocalDate passwordResetOn) {
        this.passwordResetOn = passwordResetOn;
    }
}