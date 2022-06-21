package com.gametrader.gametraderuserservice.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String message) {
        super(String.format("Username %s is taken", message));
    }
}
