package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.util.JwtUtil;
@RestController
public class AuthenticationApi {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService detailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest authRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username/password");

		}
		UserDetails loadUserByUsername = detailsService.loadUserByUsername(authRequest.getUsername());
		String generateToken = jwtUtil.generateToken(loadUserByUsername);
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(generateToken), HttpStatus.OK);
	}
}
