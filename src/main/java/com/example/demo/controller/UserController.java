package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImplementaion;

@RestController
public class UserController {

	@Autowired
	private UserServiceImplementaion service;

	@PostMapping("/addUser")
	public ResponseEntity<Response> addUserContactDetails(@RequestBody UserDto userDto) {
		UserDto details = service.addContactDetails(userDto);
		if (details != null) {
			return ResponseEntity.accepted().body(new Response(false, details, "Added Successfully", HttpStatus.OK));
		}
		return ResponseEntity.accepted().body(new Response(true, null, "Not Added", HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/getUserContactDetailsByFirstName/{firstName}")
	public ResponseEntity<Response> getUserContactDetailsByFirstName(@PathVariable String firstName) {
		User user = service.searchUserContactDetailsByFirstName(firstName);
		if (user != null) {
			return ResponseEntity.accepted()
					.body(new Response(false, user, "User Fetched Successfully", HttpStatus.OK));
		}
		return ResponseEntity.accepted().body(new Response(true, null, "Name is Not Present", HttpStatus.BAD_REQUEST));

	}

	@PutMapping("/updateUserContactDetails")
	public ResponseEntity<Response> updateUserContactDetails(@RequestBody UserDto userDto) {
		UserDto updateUserContactDetails = service.updateUserContactDetails(userDto);
		if (updateUserContactDetails != null) {
			return ResponseEntity.accepted()
					.body(new Response(false, updateUserContactDetails, "Updated  Successfully", HttpStatus.OK));
		}
		return ResponseEntity.accepted().body(new Response(true, null, "Not Updated", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/deleteUserContactDetails/{userId}")
	public ResponseEntity<Response> deleteUserContactDetails(@PathVariable Long userId) {
		Boolean deleteUserContactDetails = service.deleteUserContactDetails(userId);
		if (deleteUserContactDetails != null) {
			return ResponseEntity.accepted()
					.body(new Response(false, deleteUserContactDetails, "Deleted   Successfully", HttpStatus.OK));
		}
		return ResponseEntity.accepted().body(new Response(true, null, "Not Deleted", HttpStatus.BAD_REQUEST));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")

	@GetMapping("/getAllUserContactDetails")
	public ResponseEntity<Response> getAllUserContactDetails() {
		List<UserDto> allUserContactDetails = service.getAllUserContactDetails();
		if (!allUserContactDetails.isEmpty()) {
			return ResponseEntity.accepted()
					.body(new Response(false, allUserContactDetails, "List Fetched Successfully", HttpStatus.ACCEPTED));
		}
		return ResponseEntity.accepted()
				.body(new Response(true, null, "List of User Contact Details Is Empty", HttpStatus.BAD_GATEWAY));
	}



}
