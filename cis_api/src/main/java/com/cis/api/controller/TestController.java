package com.cis.api.controller;

import com.cis.api.dto.AssetAllotResponceDTO;
import com.cis.api.dto.AssetAllotmentResponse;
import com.cis.api.dto.AssetStoreResponseDTO;
import com.cis.api.service.MyService;

import jakarta.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    private MyService myService;

    @GetMapping("/test-feign")
    public String testFeign() {
        return myService.callAsset();
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }
    

    @GetMapping("/get-store")
    public AssetStoreResponseDTO getStore() {
        return myService.getStoreData();
    }
    
    @GetMapping("/get-allot-list")
    public AssetAllotResponceDTO getAllotList() {
    	return myService.getAllotList();
    }
    
    @GetMapping("/get-allotments")
    public AssetAllotmentResponse getAllotments(
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer subCategoryId,
            @RequestParam(required = false) Integer officeId) {

        return myService.getAllotmentsData(groupId, categoryId, subCategoryId, officeId);
    }
}