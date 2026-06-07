package com.cis.api.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SeatUserAllottedVo {
	private Long allotId;
	private Long seatId;
	private UserSetupVo userId;
	private LocalDateTime activeDate;
	private LocalDateTime deactiveDate;
	private String activeStatus;
	private String passwd;
	private String passwordReset;
	private Long officeId;
	private String otpStatus;
	private String designation;
	private String type;
	private LocalDate passwordResettedOn;
	private String otp;
	private String passwordBcrypt;
	private String salt;
}
