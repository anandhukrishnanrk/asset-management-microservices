package com.cis.api.repository;

import com.cis.api.vo.ModuleVo;
import com.cis.api.vo.TicketVo;
import com.cis.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Repository for Support Dashboard and Tickets APIs.
 */
@Repository
public class SupportRepository {

    private static final String TICKET_TYPES =
            "'Doubt Clearance','Complaints','Data Correction','Admin'";

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    // =========================================================================
    // PROJECT INFO
    // =========================================================================

    public String getProjectNameByProjectId(Integer projectId) {
        try {
            return jdbc.queryForObject(
                "SELECT order_Project_Name FROM pts.project_details WHERE project_id = ?",
                String.class, projectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    // =========================================================================
    // SUPPORT DASHBOARD — counts per ticket type (total / open / closed)
    // =========================================================================

    /**
     * Count tickets for a specific type.
     * closeStatus filter: null = all, 'Y' = closed, 'N' = open
     */
    public long countByTypeAndStatus(Integer projectId, String type, String closeStatus) {
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM pts.client_request " +
                "WHERE project_id = ? AND request_type = ?");
            List<Object> params = new ArrayList<>();
            params.add(projectId);
            params.add(type);

            if (closeStatus != null) {
                sql.append(" AND close_status = ?");
                params.add(closeStatus);
            }

            Long count = jdbc.queryForObject(sql.toString(), Long.class, params.toArray());
            return count != null ? count : 0L;
        } catch (DataAccessException e) {
            return 0L;
        }
    }

    // =========================================================================
    // TICKETS — count (for pagination)
    // =========================================================================

    /**
     * Filters: type, userId, severity, status(assignStatus), moduleId, fromDate, toDate, tag
     * Always restricts to types: Doubt Clearance, Complaints, Data Correction, Admin
     */
    public long countTickets(Integer projectId, String type, Integer userId,
                             String severity, String status, Integer moduleId,
                             Date fromDate, Date toDate, String tag) {
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM pts.client_request " +
                "WHERE project_id = :projectId " +
                "AND request_type IN ('Doubt Clearance','Complaints','Data Correction','Admin')");

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("projectId", projectId);

            appendFilters(sql, params, type, userId, severity, status, moduleId, fromDate, toDate, tag);

            Long count = namedJdbc.queryForObject(sql.toString(), params, Long.class);
            return count != null ? count : 0L;
        } catch (DataAccessException e) {
            return 0L;
        }
    }

    // =========================================================================
    // TICKETS — paginated list
    // =========================================================================


    public List<TicketVo> getTickets(Integer projectId, int firstResult, int maxResult,
    	    /**
    	     * Mirrors getRequestListOrderByRequestDate()
    	     */         String type, Integer userId, String severity,
                                     String status, Integer moduleId,
                                     Date fromDate, Date toDate, String tag) {
        try {
            StringBuilder sql = new StringBuilder(
                    "SELECT " +
                            "  csr.request_Id          AS request_id, " +
                            "  csr.ticket_No           AS ticket_no, " +
                            "  csr.request_Type        AS request_type, " +
                            "  csr.severity, " +
                            "  csr.priority            AS prority, " +
                            "  csr.request_Descr       AS req_description, " +
                            "  csr.status, " +
                            "  csr.assigned_Status     AS assign_status, " +
                            "  csr.close_Status        AS close_status, " +
                            "  csr.request_From        AS request_from, " +
                            "  csr.request_Mode        AS request_mode, " +
                            "  csr.phone_No            AS phone_no, " +
                            "  csr.email_Id            AS email_id, " +
                            "  csr.tag, " +
                            "  csr.category, " +
                            "  csr.root_Cause          AS root_cause, " +
                            "  csr.action_Taken        AS action_taken, " +
                            "  csr.progress, " +
                            "  csr.uat_Status          AS uat_status, " +
                            "  csr.sla_status, " +
                            "  csr.time_taken, " +
                            "  csr.request_Date        AS request_date, " +
                            "  csr.entered_Date        AS entered_date, " +
                            "  csr.close_Date          AS close_date, " +
                            "  csr.scheduled_Date      AS scheduled_date, " +
                            "  csr.excepted_Release_Date AS excepted_release_date, " +
                            "  csr.assigned_To         AS assigned_to, " +
                            "  csr.assigned_On         AS assigned_on, " +
                            "  csr.planned_Effort      AS planned_effort, " +
                            "  csr.project_Id          AS project_id, " +
                            "  p.order_Project_Name    AS project_name, " +
                            "  csr.module_Id           AS module_id, " +
                            "  m.module_Name           AS module_name, " +
                            "  csr.entered_Pts_User_Id AS entered_user_id, " +
                            "  u.user_Name             AS entered_user_name, " +
                            "  (SELECT ku.user_Name " +
                            "   FROM pts.task_assigmments ta " +
                            "   INNER JOIN kranmaster.user_setup ku ON ta.user_Id = ku.user_Id " +
                            "   WHERE ta.request_Id = csr.request_Id " +
                            "   ORDER BY ta.t_A_Id DESC LIMIT 1) AS task_user_name, " +
                            "  (SELECT rc.root_Cause " +
                            "   FROM pts.cis_root_cause_setup rc " +
                            "   WHERE rc.id = csr.root_Cause_Id LIMIT 1) AS root_cause_name " +
                            "FROM pts.client_request csr " +
                            "LEFT JOIN pts.project_details p  ON csr.project_Id = p.project_Id " +
                            "LEFT JOIN pts.module_setup m      ON csr.module_Id = m.module_Id " +
                            "LEFT JOIN kranmaster.user_setup u ON csr.entered_Pts_User_Id = u.user_Id " +
                            "WHERE csr.project_Id = :projectId " +
                            "AND csr.request_Type IN ('Doubt Clearance','Complaints','Data Correction','Admin')");

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("projectId", projectId);

            appendFilters(sql, params, type, userId, severity, status, moduleId, fromDate, toDate, tag);
            if (fromDate != null || toDate != null) {
                sql.append(" ORDER BY csr.request_date ASC");
            } else {
                sql.append(" ORDER BY csr.request_date DESC");
            }

            // Pagination
            sql.append(" LIMIT :limit OFFSET :offset");
            params.addValue("limit", maxResult);
            params.addValue("offset", firstResult);

            return namedJdbc.query(sql.toString(), params, (rs, rowNum) -> {
                TicketVo t = new TicketVo();
                t.setRequestId(rs.getInt("request_id"));
                t.setTicketNo(rs.getString("ticket_no"));
                t.setRequestType(rs.getString("request_type"));
                t.setSeverity(rs.getString("severity"));
                t.setPrority(rs.getString("prority"));

                // Truncate description to 100 chars + "....." (mirrors original JSP logic)
                String desc = rs.getString("req_description");
                if (desc != null) {
                    t.setSubject(desc);  // full desc as subject
                    if (desc.length() >= 100) {
                        desc = desc.substring(0, 100) + ".....";
                    }
                    t.setReqDescription(desc);
                }

                t.setStatus(rs.getString("status"));
                t.setAssignStatus(rs.getString("assign_status"));
                t.setCloseStatus(rs.getString("close_status"));
                t.setRequestFrom(rs.getString("request_from"));
                t.setRequestMode(rs.getString("request_mode"));
                t.setPhoneNo(rs.getString("phone_no"));
                t.setEmailId(rs.getString("email_id"));
                t.setTag(rs.getString("tag"));
                t.setCategory(rs.getString("category"));
                t.setRootCause(rs.getString("root_cause"));
                t.setActionTaken(rs.getString("action_taken"));
                t.setProgress(rs.getString("progress"));
                t.setUatStatus(rs.getString("uat_status"));
                t.setSlaStatus(rs.getString("sla_status"));
                t.setTimeTaken(rs.getString("time_taken"));
                t.setRequestDate(rs.getTimestamp("request_date"));
                t.setEnteredDate(rs.getTimestamp("entered_date"));
                t.setCloseDate(rs.getTimestamp("close_date"));
                t.setScheduledDate(rs.getTimestamp("scheduled_date"));
                t.setExceptedReleaseDate(rs.getTimestamp("excepted_release_date"));
                t.setAssignedTo(rs.getString("assigned_to"));
                t.setAssignedOn(rs.getTimestamp("assigned_on"));

                double pe = rs.getDouble("planned_effort");
                t.setPlannedEffort(rs.wasNull() ? null : pe);
                t.setPlannedEffortHours(null);
                t.setPlannedEffortMins(null);

                t.setProjectId(rs.getInt("project_id"));
                t.setProjectName(rs.getString("project_name"));

                int mid = rs.getInt("module_id");
                t.setModuleId(rs.wasNull() ? null : mid);
                t.setModuleName(rs.getString("module_name"));

                int uid = rs.getInt("entered_user_id");
                t.setEnteredUserId(rs.wasNull() ? null : uid);
                t.setEnteredUserName(rs.getString("entered_user_name"));

                t.setTaskAssignedUserName(rs.getString("task_user_name"));
                t.setRootCauseName(rs.getString("root_cause_name"));

                return t;
            });

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    // =========================================================================
    // MODULE LIST — for dropdown metadata
    // =========================================================================

    /**
     * Mirrors getallmodulesunderproject()
     */
    public List<ModuleVo> getModulesByProjectId(Integer projectId) {
        try {
            return jdbc.query(
                "SELECT module_id, module_name FROM pts.module_setup WHERE project_id = ?",
                (rs, rowNum) -> new ModuleVo(rs.getInt("module_id"), rs.getString("module_name")),
                projectId);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    // =========================================================================
    // TAG LIST — for dropdown metadata
    // =========================================================================

    /**
     * Mirrors getallTagsUnderProject()
     */
    public List<String> getTagsByProjectId(Integer projectId) {
        try {
            return jdbc.queryForList(
                "SELECT DISTINCT tag FROM pts.client_request " +
                "WHERE project_id = ? AND tag IS NOT NULL",
                String.class, projectId);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    // =========================================================================
    // USER LIST — users who have raised tickets in this project
    // =========================================================================

    /**
     * Mirrors getUserListByProjectId() — returns distinct users who entered tickets
     */
    public List<UserVo> getUsersByProjectId(Integer projectId) {
        try {
            return jdbc.query(
                "SELECT DISTINCT u.user_id, CONCAT(u.user_name, ' ', COALESCE(u.last_name, '')) AS full_name " +
                "FROM kranmaster.user_setup u " +
                "WHERE u.user_id IN ( " +
                "    SELECT DISTINCT entered_user_id FROM pts.client_request " +
                "    WHERE project_id = ? AND entered_user_id IS NOT NULL " +
                ") AND u.active_status = 'Y'",
                (rs, rowNum) -> new UserVo(rs.getInt("user_id"), rs.getString("full_name")),
                projectId);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    // =========================================================================
    // PRIVATE HELPER — builds dynamic WHERE clause (shared by count + list)
    // =========================================================================

    private void appendFilters(StringBuilder sql, MapSqlParameterSource params,
                                String type, Integer userId, String severity,
                                String status, Integer moduleId,
                                Date fromDate, Date toDate, String tag) {

        if (type != null && !type.isEmpty() && !type.equals("All Tickets")) {
            sql.append(" AND request_type = :type");
            params.addValue("type", type);
        }
        if (userId != null && userId != 0) {
            sql.append(" AND entered_user_id = :userId");
            params.addValue("userId", userId);
        }
        if (severity != null && !severity.isEmpty()) {
            sql.append(" AND severity = :severity");
            params.addValue("severity", severity);
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND assign_status = :status");
            params.addValue("status", status);
        }
        if (moduleId != null && moduleId != 0) {
            sql.append(" AND module_id = :moduleId");
            params.addValue("moduleId", moduleId);
        }
        if (fromDate != null) {
            sql.append(" AND DATE(request_date) >= :fromDate");
            params.addValue("fromDate", fromDate);
        }
        if (toDate != null) {
            sql.append(" AND DATE(request_date) <= :toDate");
            params.addValue("toDate", toDate);
        }
        if (tag != null && !tag.isEmpty()) {
            sql.append(" AND tag = :tag");
            params.addValue("tag", tag);
        }
    }
}
