package com.cis.api.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReleaseUploadDetailsVo {
    private Long uploadId;
    private ReleaseDetailsVo release;
    private String fileName;
    private LocalDateTime uploadDate;
}
