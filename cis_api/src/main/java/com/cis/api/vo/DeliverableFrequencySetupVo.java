package com.cis.api.vo;

import lombok.Data;

@Data
public class DeliverableFrequencySetupVo {
    private Long id;
    private String frequencyType;
    private String description;
    private String frequency;
    private String deleteStatus;
}
