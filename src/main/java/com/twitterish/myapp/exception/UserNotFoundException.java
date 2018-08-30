package com.twitterish.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User was not found in the system")
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 100L;
}
