package com.asset.asset_api.entity;

import com.asset.asset_api.util.MaskParam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;      // IMPORTANT ✔
import java.util.Date;

@Entity
@Table(schema = "kranmaster", catalog = "kranmaster", name = "user_setup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Integer id;

    @Column(name = "employee_Id")
    private String employeeId;

    @Column(name = "title_Name")
    private String titleName;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    // FIXED
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "active_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;

    @Column(name = "deactive_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactiveDate;

    @Column(name = "active_Status")
    private String activeStatus;

    @Column(name = "user_Code")
    private String userCode;

    @Column(name = "pf_No")
    private String pfNo;

    @Column(name = "user_Photo")  // FIXED
    private String userPhoto;

    @Column(name = "designation")
    private String designation;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "passwd")
    private String password;

    @Column(name = "passwd_Reset")     // FIXED
    private String passwordReset;

    @Column(name = "passwd_Reset_On")  // FIXED
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordResetOn;

    @Column(name = "admin_id")
    private Integer adminId;

    public String idEnc() {
        return new MaskParam().mask(null, this.id.toString());
    }

    public String idEnc(String target) {
        return new MaskParam().mask(target, this.id.toString());
    }
}