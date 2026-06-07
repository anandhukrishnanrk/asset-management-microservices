package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ImpUploadDetailsVo {
    private Integer uploadId;
    private ImpDetailsVo imp;
    private String fileName;
    private Date uploadDate;
}
