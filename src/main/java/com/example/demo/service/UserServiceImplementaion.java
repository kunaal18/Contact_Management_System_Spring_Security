package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserIdNotExistException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImplementaion implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDto addContactDetails(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User save = repository.save(user);
		BeanUtils.copyProperties(save, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUserContactDetails(UserDto userDto) {
		User user = repository.findById(userDto.getUserId()).orElseThrow(
				() -> new UserIdNotExistException("User is not present with this Id: " + userDto.getUserId()));
		BeanUtils.copyProperties(userDto, user);
		User save = repository.save(user);
		BeanUtils.copyProperties(save, userDto);
		return userDto;

	}

	@Override
	public Boolean deleteUserContactDetails(Long userId) {
		boolean isDeleted = false;
		User user = repository.findById(userId)
				.orElseThrow(() -> new UserIdNotExistException("User is not present with this Id: " + userId));
		repository.deleteById(userId);
		isDeleted = true;
		return isDeleted;

	}

	@Override
	public List<UserDto> getAllUserContactDetails() {
		List<User> findAll = repository.findAll();
		List<UserDto> list = new ArrayList<>();
		findAll.forEach(i -> {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(i, userDto);
			list.add(userDto);
		});
		return list;

	}

	@Override
	public User searchUserContactDetailsByFirstName(String firstName) {
		Optional<User> user = repository.findByFirstName(firstName);
		if (!user.isEmpty()) {
			User user2 = user.get();
			return user2;

		}
		return null;

	}

//	@Override
//	public List<User> searchContactByfirstName(String firstName) {
//		List<User> searchContactByFirstName = repository.searchContactByfirstName(firstName);
//		if (searchContactByFirstName.isEmpty()) {
//			return Collections.emptyList();
//		}
//		return searchContactByFirstName;
//	}
//
//	@Override
//	public List<User> searchContactlastname(String lname) {
//		List<User> searchContactlastname = repository.searchContactlastname(lname);
//		if (searchContactlastname.isEmpty()) {
//			return Collections.emptyList();
//		}
//		return searchContactlastname;
//	}

}
