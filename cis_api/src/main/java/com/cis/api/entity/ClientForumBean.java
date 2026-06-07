package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pts.client_forum_details", catalog = "pts")
@Data
public class ClientForumBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_Id")
    private Integer forumId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "subject_Id", nullable = false)
    private ClientForumSubjectBean subjectIDForum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "added_By", nullable = false)
    private Client forumAddedBy;

    @Column(name = "forum")
    private String forum;

    @Column(name = "forum_Description", length = 12, nullable = false)
    private String forumDescription;

    @Column(name = "added_Date", length = 12, nullable = false)
    private String forumDate;
}
