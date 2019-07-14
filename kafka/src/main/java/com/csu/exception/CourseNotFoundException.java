package com.csu.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String code) {
        super(code);
    }
}
