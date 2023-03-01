package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
	UserDto addContactDetails(UserDto userDto);

	User searchUserContactDetailsByFirstName(String firstName);

	UserDto updateUserContactDetails(UserDto userDto);
	
	Boolean deleteUserContactDetails(Long userId);
	
	List<UserDto> getAllUserContactDetails();
	


}
