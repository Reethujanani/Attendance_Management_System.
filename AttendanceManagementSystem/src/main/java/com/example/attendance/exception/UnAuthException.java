package com.example.attendance.exception;

public class UnAuthException extends RuntimeException{
    private String message;

    public UnAuthException(String already_available) {
        this.message = already_available;
    }
}
