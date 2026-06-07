package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class UserSetupVo {
    private Long id;
    private String employeeId;
    private String titleName;
    private String userName;
    private String lastName;
    private String gender;
    private Date dob;
    private Date activeDate;
    private Date deactiveDate;
    private String activeStatus;
    private String userCode;
    private String pfNo;
    private String userPhoto;
    private String designation;
    private String address;
    private String email;
    private String mobile;
    private String password;
    private String passwordReset;
    private Date passwordResetOn;
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
}
