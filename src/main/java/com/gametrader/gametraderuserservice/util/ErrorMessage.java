package com.gametrader.gametraderuserservice.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

}
