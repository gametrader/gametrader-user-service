package com.gametrader.gametraderuserservice.dto;

import lombok.Getter;
import lombok.Setter;
import com.gametrader.gametraderuserservice.util.Gender;

import java.time.LocalDate;

@Getter
@Setter
public class AppUserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
}
