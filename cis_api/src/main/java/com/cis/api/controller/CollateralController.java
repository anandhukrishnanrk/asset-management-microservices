package com.cis.api.controller;

import com.cis.api.model.Collateral;
import com.cis.api.model.Comment;
import com.cis.api.service.CollateralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaterals")
@CrossOrigin(origins = "*")
public class CollateralController {

    @Autowired
    private CollateralService collateralService;

//    @GetMapping
//    public List<Collateral> getCollaterals(@RequestParam Long projectId) {
//        return collateralService.getCollateralsByProjectId(projectId);
//    }

    @GetMapping("/{id}")
    public Collateral getCollateral(@PathVariable Long id) {
        return collateralService.getCollateralById(id).orElse(null);
    }

    @PutMapping("/{id}/approve")
    public Collateral approveCollateral(@PathVariable Long id) {
        return collateralService.approveCollateral(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return collateralService.getComments(id);
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return collateralService.addComment(id, comment);
    }
}
