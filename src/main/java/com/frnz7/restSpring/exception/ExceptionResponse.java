package com.frnz7.restSpring.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp,String message, String details) {
}
