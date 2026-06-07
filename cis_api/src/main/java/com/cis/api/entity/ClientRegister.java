package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "client_register", schema = "pts", catalog = "pts")
@Data
public class ClientRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_Register_Id")
    private Long clientRegisterId;

    // @ManyToOne
    // @JoinColumn(name = "client_Id")
    // private AssociateSetup client;

    // @ManyToOne
    // @JoinColumn(name = "category_Id")
    // private CategorySetup category;

    // @ManyToOne
    // @JoinColumn(name = "country_Id")
    // private CountryBean country;

    // @ManyToOne
    // @JoinColumn(name = "state_Id")
    // private StateBean state;

    // @ManyToOne
    // @JoinColumn(name = "district_Id")
    // private DistrictBean district;

    @Column(name = "client_Name", length = 300)
    private String clientName;

    @Column(name = "client_Address", columnDefinition = "TEXT")
    private String clientAddress;

    @Column(name = "client_Address2", columnDefinition = "TEXT")
    private String clientAddress2;

    @Column(name = "pinCode", length = 6)
    private String pinCode;

    @Column(name = "client_Phone1", length = 50)
    private String clientPhone1;

    @Column(name = "area_Code", length = 50)
    private String areaCode;

    @Column(name = "area_Number", length = 50)
    private String areaNumber;

    @Column(name = "e_Mail", length = 50)
    private String email;

    @Column(name = "client_Fax", length = 50)
    private String clientFax;

    @Column(name = "contact_Person", length = 50)
    private String contactPerson;

    @Column(name = "mobile_Number", length = 50)
    private String mobileNumber;

    @Column(name = "contact_Designation", length = 30)
    private String contactDesignation;

    @Column(name = "seat_Id")
    private Integer seatId;

    @Column(name = "user_Id")
    private Integer userId;
}
