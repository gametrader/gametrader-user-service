package com.gametrader.gametraderuserservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.gametrader.gametraderuserservice.util.Gender;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AppUserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
}
