package com.lms.Employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class JoinerMentorConnectionAlreadyExistsException extends RuntimeException{
    public JoinerMentorConnectionAlreadyExistsException(String message){
        super(message);
    }
}
