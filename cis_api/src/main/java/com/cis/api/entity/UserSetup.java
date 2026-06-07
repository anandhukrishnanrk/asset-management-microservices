package com.cis.api.entity;

import com.cis.api.util.MaskParam;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "kranmaster", catalog = "kranmaster", name = "user_setup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Long id;

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
    @Column(name = "dob")
    private Date dob;

    @Column(name = "active_Date")
    private Date activeDate;
    @Column(name = "deactive_Date")
    private Date deactiveDate;
    @Column(name = "active_Status")
    private String activeStatus;

    @Column(name = "user_Code")
    private String userCode;
    @Column(name = "pf_No")
    private String pfNo;
    @Column(name = "user_photo")
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
    @Column(name = "passwd_reset")
    private String passwordReset;
    @Column(name = "passwd_reset_On")
    private Date passwordResetOn;
    @Column(name = "admin_id")
    private Integer adminId;

    public String getUserFullName() {
        StringBuilder fullname = new StringBuilder();
        if (this.getUserName() != null) {
            // if (this.getTitleName() != null) {
            // fullname.append(this.getTitleName().trim() + " ");
            // }

            fullname.append(this.getUserName().trim());

            if (this.getLastName() != null && !this.getLastName().isBlank()) {
                fullname.append(" " + this.getLastName().trim());
            }
        }
        return fullname.toString();
    }

    public String getFullName() {
        StringBuilder fullname = new StringBuilder();
        if (this.getUserName() != null) {

            fullname.append(this.getUserName().trim());

            if (this.getLastName() != null && !this.getLastName().isBlank()) {
                fullname.append(" " + this.getLastName().trim());
            }
        }
        return fullname.toString();
    }

    public String idEnc() {
        return new MaskParam().mask(null, this.id.toString());
    }

    public String idEnc(String target) {
        return new MaskParam().mask(target, this.id.toString());
    }

}
