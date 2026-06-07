package com.cis.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String content;

    private String authorName;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "collateral_id")
    private Collateral collateral;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
