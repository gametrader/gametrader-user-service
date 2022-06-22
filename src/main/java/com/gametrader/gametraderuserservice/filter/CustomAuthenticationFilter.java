package com.gametrader.gametraderuserservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametrader.gametraderuserservice.model.AppUser;
import com.gametrader.gametraderuserservice.repository.AppUserRepository;
import com.gametrader.gametraderuserservice.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final AppUserRepository appUserRepository;
  private final JwtUtils jwtUtils;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationException {


    Map<String, String> requestPayload = new HashMap<>();
    try {
      requestPayload = new ObjectMapper().readValue(request.getInputStream(), Map.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(requestPayload.get("username"),
            requestPayload.get("password"));
    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authResult)
      throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();
    String requestUrl = request.getRequestURL().toString();
    AppUser appUser =
        appUserRepository.findAppUserByUsername(((User) authResult.getPrincipal()).getUsername())
            .get();
    String accessToken = jwtUtils.createAccessToken(user.getUsername(), requestUrl,
        user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()), appUser.getId());
    String refreshToken =
        jwtUtils.createRefreshToken(user.getUsername(), request.getRequestURL().toString());
    response.setHeader("access_token", accessToken);
    response.setHeader("refresh_token", refreshToken);
    Map<String, String> body = Map.of("access_token", accessToken, "refresh_token", refreshToken);
    new ObjectMapper().writeValue(response.getOutputStream(), body);
  }
}
