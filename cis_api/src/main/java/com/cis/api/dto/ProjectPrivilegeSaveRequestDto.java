package com.cis.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProjectPrivilegeSaveRequestDto {
    private Long projectId;
    private String roleName;
    private List<PrivilegeItemDto> privileges;

    @Data
    public static class PrivilegeItemDto {
        private String tabName;
        private String subtabName;
        private Boolean canRead;
        private Boolean canWrite;
        private Boolean canDelete;
        private Boolean canApprove;
        private Boolean canVerify;
    }
}
