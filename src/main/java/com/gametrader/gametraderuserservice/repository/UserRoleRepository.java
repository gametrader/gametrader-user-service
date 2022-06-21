package com.gametrader.gametraderuserservice.repository;

import com.gametrader.gametraderuserservice.model.UserRole;
import com.gametrader.gametraderuserservice.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole getByRole(Role role);
}