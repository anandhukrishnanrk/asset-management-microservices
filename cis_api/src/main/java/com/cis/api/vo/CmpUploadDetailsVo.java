package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class CmpUploadDetailsVo {
    private Long uploadId;
    private CmpDetailsVo cmpDetails;
    private String fileName;
    private Date uploadDate;
}
