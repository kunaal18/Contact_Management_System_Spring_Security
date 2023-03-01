package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	 private long userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String password;
	private String roles;
}
