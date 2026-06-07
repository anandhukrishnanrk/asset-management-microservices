package com.cis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "crs", schema = "pts", catalog = "pts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler",
        "crsRef",               // avoid infinite self-loop
        "project",
        "module",
        "group"
})
public class Crs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crs_Id")
    private Integer crsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    @JsonIgnoreProperties({"crsList"}) // if project has list<crs>
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    @JsonIgnoreProperties({"crsList"}) // if module has list<crs>
    private ModuleSetup module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crs_ref_To")
    @JsonIgnoreProperties({"crsRef"}) // avoid self recursion
    private Crs crsRef;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    @JsonIgnoreProperties({"crsList"}) // if group has list<crs>
    private ProjectGroup group;

    @Column(name = "crs_No", length = 10)
    private String crsNo;

    @Column(name = "crs_Details", length = 500)
    private String crsDetails;

    @Column(name = "tor_ref_No", length = 10)
    private String torRefNo;

    @Column(name = "crs_submit_Status", length = 5)
    private String crsSubmitStatus;

    @Column(name = "crs_entry_Type", length = 5)
    private String crsEntryType;

    @Column(name = "crs_Remark", length = 300)
    private String crsRemark;

    @Column(name = "torVersionId")
    private Integer torVersionId;

    @Column(name = "firstCrsEntryStatus", length = 10)
    private String firstCrsEntryStatus;

    @Column(name = "crsType", length = 10)
    private String crsType;

    @Column(name = "user_Id", length = 10)
    private String userId;

    @Column(name = "percentagecompleted", length = 50)
    private String percentageCompleted;

    @Column(name = "crsstatus_Fortask", length = 50)
    private String crsStatusForTask;

    @Column(name = "task_Id", length = 50)
    private String taskId;

    @Column(name = "completed_Date")
    private Timestamp completedDate;

    @Column(name = "efforttaken", length = 50)
    private String effortTaken;

    @Column(name = "fromdate")
    private Timestamp fromDate;

    @Column(name = "fromtime")
    private String fromTime;

    @Column(name = "totime", length = 30)
    private String toTime;

    @Column(name = "attachment", length = 50)
    private String attachment;

    @Column(name = "dependent_Requirement")
    private String dependentRequirement;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "approve_Status")
    private String approveStatus;

    @Column(name = "types")
    private String types;
    
    @OneToMany(mappedBy = "crs", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("crs")
    private List<Srs> srslist;
}
