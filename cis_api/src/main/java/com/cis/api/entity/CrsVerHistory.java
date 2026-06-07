package com.cis.api.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crs_ver_history", schema = "pts", catalog = "pts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CrsVerHistory {

    @Id
    @Column(name = "crs_Ver_His_Id")
    private Integer crsVerHisId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crs_version_Id")
    @JsonIgnoreProperties({"crsVerHistories"})   // prevent recursion
    private CrsVersion crsVer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crs_Id")
    @JsonIgnoreProperties({"crsVerHistories"})   // prevent recursion
    private Crs crs;

    @Column(name = "crs_Point")
    private String crsPoint;

    @Column(name = "crs_Ref_No")
    private String crsRefNo;

    @Column(name = "status", length = 2)
    private String status;

    @Column(name = "date")
    private String date;

    @Column(name = "action")
    private String action;

    @Column(name = "done_By")
    private String doneBy;
}
