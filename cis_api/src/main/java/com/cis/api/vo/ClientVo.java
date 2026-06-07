package com.cis.api.vo;

import lombok.Data;

@Data
public class ClientVo {

    private Long clientId;

    private ProjectDetailsVo project;

    private String name;

    private String password;

    private Long clientLoginId;
}
