package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class TicketResponseVo {

    private Integer projectId;

    // Pagination info
    private int firstResult;
    private int maxResult;
    private long totalCount;
    private int totalPages;
    private int currentPage;

    // Filter metadata (dropdowns)
    private List<ModuleVo> moduleList;
    private List<String> tagList;
    private List<UserVo> userList;

    // Ticket data
    private List<TicketVo> tickets;

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public int getFirstResult() { return firstResult; }
    public void setFirstResult(int firstResult) { this.firstResult = firstResult; }

    public int getMaxResult() { return maxResult; }
    public void setMaxResult(int maxResult) { this.maxResult = maxResult; }

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long totalCount) { this.totalCount = totalCount; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public List<ModuleVo> getModuleList() { return moduleList; }
    public void setModuleList(List<ModuleVo> moduleList) { this.moduleList = moduleList; }

    public List<String> getTagList() { return tagList; }
    public void setTagList(List<String> tagList) { this.tagList = tagList; }

    public List<UserVo> getUserList() { return userList; }
    public void setUserList(List<UserVo> userList) { this.userList = userList; }

    public List<TicketVo> getTickets() { return tickets; }
    public void setTickets(List<TicketVo> tickets) { this.tickets = tickets; }
}
