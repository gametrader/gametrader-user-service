package com.gametrader.gametraderuserservice.repository;

import com.gametrader.gametraderuserservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsAppUserByEmail(String email);

    boolean existsAppUserByUsername(String username);

    Optional<AppUser> findAppUserByUsername(String username);
}