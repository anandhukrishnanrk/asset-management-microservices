package com.cis.api.controller;

import com.cis.api.dto.AuthRequest;
import com.cis.api.dto.AuthResponse;
import com.cis.api.security.JwtUtil;
import com.cis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		return userService.authenticate(authRequest.getUsername(), authRequest.getPassword()).map(client -> {
			String token = jwtUtil.generateToken(client.getName(), "CLIENT");
			return ResponseEntity.ok(new AuthResponse(token, client.getClientRegister().getClientRegisterId()));
		}).orElse(ResponseEntity.status(401).build());
	}
}
