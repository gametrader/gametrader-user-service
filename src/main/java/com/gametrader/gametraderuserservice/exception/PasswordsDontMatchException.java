package com.gametrader.gametraderuserservice.exception;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException() {
        super("Passwords do not match");
    }
}
