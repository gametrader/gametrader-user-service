package com.gametrader.gametraderuserservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametrader.gametraderuserservice.dto.AppUserDTO;
import com.gametrader.gametraderuserservice.exception.EmailAlreadyTakenException;
import com.gametrader.gametraderuserservice.exception.PasswordsDontMatchException;
import com.gametrader.gametraderuserservice.exception.UsernameAlreadyTakenException;
import com.gametrader.gametraderuserservice.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/v1/api/auth")
@CrossOrigin
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody AppUserDTO registerRequest) throws PasswordsDontMatchException,
            EmailAlreadyTakenException, UsernameAlreadyTakenException {
        appUserService.register(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/token/refresh")

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Map<String, String> tokens = appUserService.refreshToken(authorizationHeader,
                        request.getRequestURL().toString(), refreshToken
                );
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
