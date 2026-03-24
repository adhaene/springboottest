package com.bezkoder.spring.jpa.h2.controller;

import java.util.Date;

public class ErrorResponse {
    private Date date;

    public String getMessage() {
        return message;
    }

    private String message;
    private int value;
    public ErrorResponse(Date date, String message, int value) {
    }
}
