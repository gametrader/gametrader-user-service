package com.gametrader.gametraderuserservice.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gametrader.gametraderuserservice.dto.AppUserDTO;
import com.gametrader.gametraderuserservice.exception.EmailAlreadyTakenException;
import com.gametrader.gametraderuserservice.exception.PasswordsDontMatchException;
import com.gametrader.gametraderuserservice.exception.UsernameAlreadyTakenException;
import com.gametrader.gametraderuserservice.mapper.AppUserMapper;
import com.gametrader.gametraderuserservice.model.AppUser;
import com.gametrader.gametraderuserservice.model.UserRole;
import com.gametrader.gametraderuserservice.repository.AppUserRepository;
import com.gametrader.gametraderuserservice.repository.UserRoleRepository;
import com.gametrader.gametraderuserservice.util.JwtUtils;
import com.gametrader.gametraderuserservice.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService {

  private final AppUserRepository appUserRepository;
  private final AppUserMapper appUserMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRoleRepository roleRepository;
  private final JwtUtils jwtUtils;

  @Transactional
  public void register(AppUserDTO registerRequest)
      throws EmailAlreadyTakenException, UsernameAlreadyTakenException,
      PasswordsDontMatchException {

    if (appUserRepository.existsAppUserByEmail(registerRequest.getEmail())) {
      throw new EmailAlreadyTakenException(registerRequest.getEmail());
    }

    if (appUserRepository.existsAppUserByUsername(registerRequest.getUsername())) {
      throw new UsernameAlreadyTakenException(registerRequest.getUsername());
    }
    if (!passwordsMatch(registerRequest.getPassword(), registerRequest.getMatchingPassword())) {
      throw new PasswordsDontMatchException();
    }

    AppUser registeredUser = appUserMapper.toEntity(registerRequest);
    registeredUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));


    addRoleToNewUser(registeredUser);
    appUserMapper.toDto(appUserRepository.save(registeredUser));
  }


  private boolean passwordsMatch(String password, String matchingPassword) {
    return password.equals(matchingPassword);
  }

  public Map<String, String> refreshToken(String authorizationHeader, String issuer,
                                          String refreshToken) {
    DecodedJWT decodedJWT = jwtUtils.decodeJwt(authorizationHeader);
    AppUser user = getUser(decodedJWT.getSubject());
    List<String> userRoles = user.getUserRoles()
        .stream()
        .map(role -> role.getRole().name()).
        collect(Collectors.toList());
    String accessToken = jwtUtils.createAccessToken(user.getUsername(),
        issuer,
        userRoles,
        1L
    );
    Map<String, String> tokens = new HashMap<>();
    tokens.put("access_token", accessToken);
    tokens.put("refresh_token", refreshToken);
    return tokens;
  }

  private AppUser getUser(String username) {
    return appUserRepository.findAppUserByUsername(username).orElseThrow(() -> {
      throw new NoSuchElementException(String.format("User: %s not found", username));
    });

  }

  private void addRoleToNewUser(AppUser user) {
    final UserRole userRole = roleRepository.getByRole(Role.USER);
    user.getUserRoles().add(userRole);
  }
}
