package com.example.etc.exceptions;

import org.springframework.http.HttpStatus;

public class CustomHTTPGenericException extends RuntimeException {
    private HttpStatus statusCode;

    public CustomHTTPGenericException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
