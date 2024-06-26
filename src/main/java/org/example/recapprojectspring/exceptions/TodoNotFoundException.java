package org.example.recapprojectspring.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends Exception{
    public TodoNotFoundException(String message) {
        super(message);
    }
}
