package com.cis.api.entity;

import com.cis.api.entity.PmpGdReleased;
import com.cis.api.entity.Rolesetup;

import jakarta.persistence.*;

@Entity(name = "PointsofContact")
@Table(name = "pointsofcontact", schema = "pts", catalog = "pts")
public class PointsofContact {

    @Id
    @Column(name = "p_C_Id")
    private Integer pCId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "desigination", length = 50)
    private String desigination;

    @Column(name = "telno", length = 50)
    private String telno;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "rolename", length = 10)
    private String rolename;

    @Column(name = "mode_Of_Communication", length = 100)
    private String modeOfCommunication;

    @Column(name = "frequency_Of_Contact", length = 100)
    private String frequencyOfContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pmp_Id")
    private PmpGdReleased pmp;


    // Constructors
    public PointsofContact() {}

    // Getters and Setters
    public Integer getPCId() {
        return pCId;
    }

    public void setPCId(Integer pCId) {
        this.pCId = pCId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesigination() {
        return desigination;
    }

    public void setDesigination(String desigination) {
        this.desigination = desigination;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getModeOfCommunication() {
        return modeOfCommunication;
    }

    public void setModeOfCommunication(String modeOfCommunication) {
        this.modeOfCommunication = modeOfCommunication;
    }

    public String getFrequencyOfContact() {
        return frequencyOfContact;
    }

    public void setFrequencyOfContact(String frequencyOfContact) {
        this.frequencyOfContact = frequencyOfContact;
    }

    public PmpGdReleased getPmp() {
        return pmp;
    }

    public void setPmp(PmpGdReleased pmp) {
        this.pmp = pmp;
    }

   
}