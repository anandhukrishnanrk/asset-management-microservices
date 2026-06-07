package com.cis.api.vo;

import lombok.Data;

@Data
public class TestingCountVo {
    private Long totalSrs;
    private Long totalUnitTestDefects;
    private Long totalTestCases;
    private Long failedTestCases;
    private Long testingDefects;
    private Long openBugs;
    private Long closedBugs;
    private Long productionBugs;
    private Long deferredBugs;
}
