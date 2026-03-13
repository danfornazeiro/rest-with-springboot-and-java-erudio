package com.frnz7.restSpring.exception;

import com.frnz7.restSpring.model.Greeting;

import java.util.Date;

public record ExceptionResponse(Date timestamp,String message, String details) {
}
