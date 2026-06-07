package com.cis.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskDto {
    private String userName;
    private Integer effortTaken;
    private Integer percentageCompleted;
    private String schDate;
}
