package com.gametrader.gametraderuserservice.model;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class AppUser {

    @Id
    private Long id;
    private String username;


}
