package com.cis.api.repository;

import com.cis.api.vo.WbsComponentVo;
import com.cis.api.vo.WbsGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Repository using plain JdbcTemplate + VO classes (no JPA entities).
 *
 * ⚠️  TABLE / COLUMN NAMES below follow common naming seen in typical
 *     CMMI PTS schemas. Adjust them to match YOUR actual DB schema.
 */
@Repository
public class WbsRepository {

    @Autowired
    private JdbcTemplate jdbc;

    // -------------------------------------------------------------------------
    // CMMI Project basic info
    // -------------------------------------------------------------------------

    /**
     * Returns the internal project_id (tbl_project FK) for a given cmmi_project row.
     * Adjust table/column names to match your schema.
     */
    public Integer getProjectIdByCmmiProjectId(Integer cmmiProjectId) {
        try {
            return jdbc.queryForObject(
                "SELECT project_Id FROM pts.cmmi_project WHERE id = ?",
                Integer.class, cmmiProjectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public String getProjectNameByCmmiProjectId(Integer cmmiProjectId) {
        try {
            return jdbc.queryForObject(
            		"SELECT p.order_Project_Name " +
            	            "FROM pts.project_details p " +
            	            "JOIN pts.cmmi_project c ON p.project_Id = c.project_Id " +
            	            "WHERE c.id = ?",
                String.class, cmmiProjectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    // -------------------------------------------------------------------------
    // WBS Groups  (cmmi_group table — shown as accordion headers in JSP)
    // -------------------------------------------------------------------------

    /**
     * Fetches all WBS groups for the given moduleId (0 = all modules).
     */
    public List<WbsGroupVo> getWbsGroups(Integer moduleId) {
        try {
            String sql;
            Object[] params = new Object[]{};

            if (moduleId == null || moduleId == 0) {
                // Same as: id not in (3,7,8,9,13) and displayOrder > 0 order by displayOrder
                sql = """
                    SELECT id, group_name
                    FROM pts.cmmi_group
                    WHERE id NOT IN (3,7,8,9,13)
                    AND display_order > 0
                    ORDER BY display_order
                """;
            } else {
                // Same as: id in (3,7,8,9,13) and displayOrder > 0 order by displayOrder
                sql = """
                    SELECT id, group_name
                    FROM pts.cmmi_group
                    WHERE id IN (3,7,8,9,13)
                    AND display_order > 0
                    ORDER BY display_order
                """;
            }

            return jdbc.query(sql, (rs, rowNum) -> {
                WbsGroupVo g = new WbsGroupVo();
                g.setId(rs.getInt("id"));
                g.setGroupName(rs.getString("group_name"));
                return g;
            });

        } catch (DataAccessException e) {
            e.printStackTrace(); // keep for debugging
            return new ArrayList<>();
        }
    }

    // -------------------------------------------------------------------------
    // Planned effort / dates per group  (aggregated from project components)
    // -------------------------------------------------------------------------

    public java.util.Date getGroupPlannedStartDate(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT MIN(pc.planned_start_date) " +
                         "FROM pts.cmmi_project_components pc " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    public java.util.Date getGroupPlannedEndDate(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT MAX(pc.planned_end_date) " +
                         "FROM pts.cmmi_project_components pc " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    public Double getGroupPlannedEffort(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT SUM(pc.planned_effort) " +
                         "FROM pts.cmmi_project_components pc " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, Double.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, Double.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    public java.util.Date getGroupActualStartDate(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT MIN(ta.actual_start_date) " +
                         "FROM pts.task_assigmments ta " +
                         "JOIN pts.cmmi_project_components pc ON ta.cmmi_Project_Component_Id = pc.id " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    public java.util.Date getGroupActualEndDate(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT MAX(ta.actual_end_date) " +
                         "FROM pts.task_assignments ta " +
                         "JOIN pts.cmmi_project_components pc ON ta.cmmi_Project_Component_Id = pc.id " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, java.util.Date.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    public Double getGroupActualEffort(Integer cmmiProjectId, Integer groupId, Integer moduleId) {
        try {
            String sql = "SELECT SUM(ta.actual_effort) " +
                         "FROM pts.task_assignments ta " +
                         "JOIN pts.cmmi_project_components pc ON ta.cmmi_Project_Component_Id = pc.id " +
                         "JOIN pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                         "WHERE pc.cmmi_project_id = ? AND sg.group_id = ?" +
                         (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "");
            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, Double.class, cmmiProjectId, groupId, moduleId);
            return jdbc.queryForObject(sql, Double.class, cmmiProjectId, groupId);
        } catch (DataAccessException e) { return null; }
    }

    // -------------------------------------------------------------------------
    // Project Components  (the rows inside each accordion group)
    // -------------------------------------------------------------------------

    /**
     * Fetches all project components for a cmmiProjectId.
     * Audit components (group_id = 11) are included; filtering for assigned audits
     * is handled in the service layer.
     */
    public List<WbsComponentVo> getProjectComponents(Integer cmmiProjectId, Integer moduleId) {
        try {
            String sql =
                "SELECT pc.id            AS component_id, " +
                "       sg.item_name     AS item_name, " +
                "       sg.group_id      AS group_id, " +
                "       cg.group_name    AS group_name, " +
                "       sg.audit_id      AS audit_id, " +
                "       pc.planned_start_date, " +
                "       pc.planned_end_date, " +
                "       pc.planned_effort, " +
                "       pc.actual_start_date, " +
                "       pc.actual_end_date, " +
                "       pc.actual_effort, " +
                "       pc.module_id " +
                "FROM   pts.cmmi_project_components pc " +
                "JOIN   pts.cmmi_sub_group sg ON pc.component_id = sg.id " +
                "JOIN   pts.cmmi_group cg     ON sg.group_id = cg.id " +
                "WHERE  pc.cmmi_project_id = ?" +
                (moduleId != null && moduleId != 0 ? " AND pc.module_id = ?" : "") +
                " ORDER BY cg.id, sg.id";

            RowMapper<WbsComponentVo> mapper = (rs, rowNum) -> {
                WbsComponentVo v = new WbsComponentVo();
                v.setComponentId(rs.getInt("component_id"));
                v.setItemName(rs.getString("item_name"));
                v.setGroupId(rs.getInt("group_id"));
                v.setGroupName(rs.getString("group_name"));
                int auditId = rs.getInt("audit_id");
                v.setAuditId(rs.wasNull() ? null : auditId);
                v.setPlannedStartDate(rs.getDate("planned_start_date"));
                v.setPlannedEndDate(rs.getDate("planned_end_date"));
                double pe = rs.getDouble("planned_effort");
                v.setPlannedEffort(rs.wasNull() ? null : pe);
                v.setActualStartDate(rs.getDate("actual_start_date"));
                v.setActualEndDate(rs.getDate("actual_end_date"));
                double ae = rs.getDouble("actual_effort");
                v.setActualEffort(rs.wasNull() ? null : ae);
                int mid = rs.getInt("module_id");
                v.setModuleId(rs.wasNull() ? null : mid);
                return v;
            };

            if (moduleId != null && moduleId != 0)
                return jdbc.query(sql, mapper, cmmiProjectId, moduleId);
            return jdbc.query(sql, mapper, cmmiProjectId);

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    // -------------------------------------------------------------------------
    // Assigned Audit IDs for the project  (used to filter audit components)
    // -------------------------------------------------------------------------

    public List<Integer> getAssignedAuditIdsByProjectId(Integer projectId) {
        try {
            String sql = """
                SELECT DISTINCT pfa.audit_Id
                FROM pts.projects_for_audit pfa
                WHERE pfa.project_Id = ?
                AND pfa.active_Status = 'Y'
            """;

            List<Integer> auditIds = jdbc.queryForList(sql, Integer.class, projectId);

            return (auditIds != null && !auditIds.isEmpty())
                    ? auditIds
                    : new ArrayList<>();

        } catch (DataAccessException e) {
            e.printStackTrace(); // keep while debugging
            return new ArrayList<>();
        }
    }

    // -------------------------------------------------------------------------
    // Effort / date summary fields  (shown as hoursWorked in original JSP)
    // -------------------------------------------------------------------------

    public String getTotalHoursWorkedOnCoding(Integer projectId, Integer moduleId) {

        try {

            String sql =
                "SELECT ta.efforttaken " +
                "FROM pts.srs s " +
                "JOIN pts.task_assigmments ta ON s.task_Id = ta.t_A_Id " +
                "WHERE s.project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND s.module_Id = ?" : "");

            List<String> effortHours =
                    (moduleId != null && moduleId != 0)
                    ? jdbc.queryForList(sql, String.class, projectId, moduleId)
                    : jdbc.queryForList(sql, String.class, projectId);

            int hours = 0, mins = 0;

            for (String effort : effortHours) {
                if (effort == null) continue;

                String[] parts = effort.split("hrs");
                hours += Integer.parseInt(parts[0].trim());

                if (parts.length > 1) {
                    String[] m = parts[1].split("mins");
                    mins += Integer.parseInt(m[0].trim());
                }
            }

            hours += mins / 60;
            mins = mins % 60;

            return hours + "." + mins;

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public String getTotalHoursWorkedOnBug(Integer projectId, Integer moduleId) {

        try {

            String sql =
                "SELECT ta.efforttaken " +
                "FROM pts.task_assigmments ta " +
                "LEFT JOIN pts.client_request cr ON ta.t_A_Id = cr.task_Id " +
                "WHERE cr.request_Type = 'Bug' " +
                "AND cr.project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND ta.module_Id = ?" : "");

            List<String> effortHours =
                    (moduleId != null && moduleId != 0)
                    ? jdbc.queryForList(sql, String.class, projectId, moduleId)
                    : jdbc.queryForList(sql, String.class, projectId);

            int hours = 0;
            int mins = 0;

            for (String effort : effortHours) {

                if (effort == null || effort.trim().isEmpty())
                    continue;

                effort = effort.toLowerCase().trim();   // normalize case

                String[] hSplit = effort.split("hrs");
                hours += Integer.parseInt(hSplit[0].trim());

                if (hSplit.length > 1) {
                    String[] mSplit = hSplit[1].split("mins");
                    mins += Integer.parseInt(mSplit[0].trim());
                }
            }

            hours += mins / 60;
            mins = mins % 60;

            return hours + "." + mins;

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public String getTotalHoursWorkedOnDefect(Integer cmmiProjectId, Integer moduleId) {

        try {

            String sql =
                "SELECT ta.efforttaken " +
                "FROM pts.task_assigmments ta " +
                "LEFT JOIN pts.peer_review_details prd ON ta.t_A_Id = prd.taskids " +
                "LEFT JOIN pts.peer_review_base prb ON prb.id = prd.base_Id " +
                "WHERE prb.cmmi_Project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND ta.module_Id = ?" : "");

            List<String> effortHours =
                (moduleId != null && moduleId != 0)
                ? jdbc.queryForList(sql, String.class, cmmiProjectId, moduleId)
                : jdbc.queryForList(sql, String.class, cmmiProjectId);


            int hours = 0;
            int mins = 0;

            for (String effort : effortHours) {

                if (effort == null) continue;

                effort = effort.toLowerCase();

                Matcher h = Pattern.compile("(\\d+)\\s*h").matcher(effort);
                Matcher m = Pattern.compile("(\\d+)\\s*m").matcher(effort);

                if (h.find())
                    hours += Integer.parseInt(h.group(1));

                if (m.find())
                    mins += Integer.parseInt(m.group(1));
            }

            hours += mins / 60;
            mins = mins % 60;

            return hours + "." + mins;

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    // Date helpers for coding / bug / defect phases
//    public java.util.Date getCodingStartDate(Integer projectId, Integer moduleId) {
//        return queryMinDate("CODING", projectId, moduleId, "planned_start_date");
//    }
    
    public Date getCodingStartDate(Integer projectId, Integer moduleId) {

        try {

            String sql =
                "SELECT MIN(ta.completedDate) " +
                "FROM pts.srs s " +
                "JOIN pts.task_assigmments ta ON s.task_Id = ta.t_A_Id " +
                "WHERE s.project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND s.module_Id = ?" : "");

            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, java.util.Date.class, projectId, moduleId);
            else
                return jdbc.queryForObject(sql, java.util.Date.class, projectId);

        } catch (DataAccessException e) {
            return null;
        }
    }
//    public java.util.Date getCodingEndDate(Integer projectId, Integer moduleId) {
//        return queryMaxDate("CODING", projectId, moduleId, "planned_end_date");
//    }
    public Date getCodingEndDate(Integer projectId, Integer moduleId) {
        try {

            String sql =
                "SELECT MAX(ta.completedDate) " +
                "FROM pts.srs s " +
                "JOIN pts.task_assigmments ta ON s.task_Id = ta.t_A_Id " +
                "WHERE s.project_Id = ? " +
                "AND ta.status IS NULL " +
                (moduleId != null && moduleId != 0 ? " AND s.module_Id = ?" : "");

            if (moduleId != null && moduleId != 0) {
                return jdbc.queryForObject(sql, Date.class, projectId, moduleId);
            } else {
                return jdbc.queryForObject(sql, Date.class, projectId);
            }

        } catch (DataAccessException e) {
            return null;
        }
    }
    
//    public java.util.Date getBugStartDate(Integer projectId) {
//        return queryMinDateNoModule("BUG", projectId, "planned_start_date");
//    }
    
    public Date getBugStartDate(Integer projectId) {
        try {
            String sql =
                "SELECT MIN(ta.fromDate) " +
                "FROM pts.task_assigmments ta " +
                "JOIN pts.client_request cr ON ta.t_A_Id = cr.task_Id " +
                "WHERE cr.request_Type = 'Bug' " +
                "AND cr.project_Id = ?";

            List<Date> list = jdbc.queryForList(sql, Date.class, projectId);
            return list.isEmpty() ? null : list.get(0);

        } catch (Exception e) {
            return null;
        }
    }
//    public java.util.Date getBugEndDate(Integer projectId) {
//        return queryMaxDateNoModule("BUG", projectId, "planned_end_date");
//    }
    public Date getBugEndDate(Integer projectId) {
        try {
            String sql =
                "SELECT MAX(ta.completedDate) " +
                "FROM pts.task_assigmments ta " +
                "JOIN pts.client_request cr ON ta.t_A_Id = cr.task_Id " +
                "WHERE cr.request_Type = 'Bug' " +
                "AND cr.project_Id = ?";

            List<Date> list = jdbc.queryForList(sql, Date.class, projectId);
            return list.isEmpty() ? null : list.get(0);

        } catch (Exception e) {
            return null;
        }
    }
    
//    public java.util.Date getDefectStartDate(Integer projectId, Integer moduleId) {
//        return queryMinDate("DEFECT", projectId, moduleId, "planned_start_date");
//    }
//    public java.util.Date getDefectEndDate(Integer projectId, Integer moduleId) {
//        return queryMaxDate("DEFECT", projectId, moduleId, "planned_end_date");
//    }
    
    public Date getDefectStartDate(Integer cmmiProjectId, Integer moduleId) {
        try {
            String sql =
                "SELECT MIN(ta.fromDate) " +
                "FROM pts.task_assigmments ta " +
                "LEFT JOIN pts.peer_review_details prd ON ta.t_A_Id = prd.taskids " +
                "LEFT JOIN pts.peer_review_base prb ON prb.id = prd.base_Id " +
                "WHERE prb.cmmi_Project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND ta.module_Id = ?" : "");

            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, Date.class, cmmiProjectId, moduleId);

            return jdbc.queryForObject(sql, Date.class, cmmiProjectId);

        } catch (DataAccessException e) {
            return null;
        }
    }
    
    public Date getDefectEndDate(Integer cmmiProjectId, Integer moduleId) {
        try {
            String sql =
                "SELECT MAX(ta.completedDate) " +
                "FROM pts.task_assigmments ta " +
                "LEFT JOIN pts.peer_review_details prd ON ta.t_A_Id = prd.taskids " +
                "LEFT JOIN pts.peer_review_base prb ON prb.id = prd.base_Id " +
                "WHERE prb.cmmi_Project_Id = ? " +
                (moduleId != null && moduleId != 0 ? "AND ta.module_Id = ?" : "");

            if (moduleId != null && moduleId != 0)
                return jdbc.queryForObject(sql, Date.class, cmmiProjectId, moduleId);

            return jdbc.queryForObject(sql, Date.class, cmmiProjectId);

        } catch (DataAccessException e) {
            return null;
        }
    }

    public Integer getCmmiProjectIdByProjectId(Integer projectId) {
        try {
            return jdbc.queryForObject(
                "SELECT id FROM pts.cmmi_project WHERE project_id = ?",
                Integer.class, projectId);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
