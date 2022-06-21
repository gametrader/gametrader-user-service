package com.gametrader.gametraderuserservice.exception;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String message) {
        super(String.format("Email %s is taken", message));
    }
}
