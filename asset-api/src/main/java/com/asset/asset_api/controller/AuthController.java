package com.asset.asset_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.asset_api.dto.AuthRequest;
import com.asset.asset_api.dto.AuthResponse;
import com.asset.asset_api.security.JwtUtil;
import com.asset.asset_api.service.UserService;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

	    return userService.authenticate(authRequest.getUsername(), authRequest.getPassword())
	        .map(user -> {
	            String token = jwtUtil.generateToken(user.getUserName(), "CLIENT");

	            AuthResponse resp = new AuthResponse(
	                token,
	                user.getId()
	            );

	            return ResponseEntity.ok(resp);
	        })
	        .orElseGet(() ->
	            ResponseEntity.status(401).build()   // NO MESSAGE
	        );
	}}
