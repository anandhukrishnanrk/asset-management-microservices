package com.cis.api.service;

import com.cis.api.model.Collateral;
import com.cis.api.model.CollateralStatus;
import com.cis.api.model.Comment;
import com.cis.api.repository.CollateralRepository;
import com.cis.api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollateralService {

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private CommentRepository commentRepository;

//    public List<Collateral> getCollateralsByProjectId(Long projectId) {
//        return collateralRepository.findByProjectId(projectId);
//    }

    public Optional<Collateral> getCollateralById(Long id) {
        return collateralRepository.findById(id);
    }

    public Collateral saveCollateral(Collateral collateral) {
        return collateralRepository.save(collateral);
    }

    public Collateral approveCollateral(Long id) {
        return collateralRepository.findById(id).map(c -> {
            c.setStatus(CollateralStatus.APPROVED);
            return collateralRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Collateral not found"));
    }

    public Comment addComment(Long collateralId, Comment comment) {
        Collateral collateral = collateralRepository.findById(collateralId)
                .orElseThrow(() -> new RuntimeException("Collateral not found"));
        comment.setCollateral(collateral);
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long collateralId) {
        return commentRepository.findByCollateralIdOrderByTimestampDesc(collateralId);
    }
}
