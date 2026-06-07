package com.cis.api.vo;

import lombok.Data;

@Data
public class ClientRegisterVo {
    private Long clientRegisterId;
    private String clientName;
    private String clientAddress;
    private String clientAddress2;
    private String pinCode;
    private String clientPhone1;
    private String areaCode;
    private String areaNumber;
    private String email;
    private String clientFax;
    private String contactPerson;
    private String mobileNumber;
    private String contactDesignation;
    private Integer seatId;
    private Integer userId;
}
