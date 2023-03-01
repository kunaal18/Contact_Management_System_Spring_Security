package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UserNotExistException.class)
	public ResponseEntity<Response> userNotPresentExceptionHandler(UserNotExistException e) {

		return ResponseEntity.badRequest().body(new Response(true, null, e.getMessage(), HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(value = UserIdNotExistException.class)
	public ResponseEntity<Response> contactIdNotExistExceptionHandler(UserIdNotExistException e) {

		return ResponseEntity.badRequest().body(new Response(true, null, e.getMessage(), HttpStatus.BAD_REQUEST));
	}

}
