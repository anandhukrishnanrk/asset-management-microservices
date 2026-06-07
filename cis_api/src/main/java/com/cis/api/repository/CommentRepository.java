package com.cis.api.repository;

import com.cis.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCollateralIdOrderByTimestampDesc(Long collateralId);
}
