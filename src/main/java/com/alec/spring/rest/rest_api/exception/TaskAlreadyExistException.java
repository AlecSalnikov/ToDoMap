package com.alec.spring.rest.rest_api.exception;


public class TaskAlreadyExistException extends Exception {
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
