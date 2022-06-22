package com.gametrader.gametraderuserservice.configuration;


import com.gametrader.gametraderuserservice.filter.CustomAuthenticationFilter;
import com.gametrader.gametraderuserservice.filter.CustomAuthorizationFilter;

import com.gametrader.gametraderuserservice.repository.AppUserRepository;
import com.gametrader.gametraderuserservice.service.UserDetailsServiceImpl;
import com.gametrader.gametraderuserservice.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;
  private final AppUserRepository appUserRepository;
  private final JwtUtils jwtUtils;


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.
        userDetailsService(userDetailsService).
        passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().disable();
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().antMatchers("/login").permitAll();
    http.addFilter(
        new CustomAuthenticationFilter(authenticationManagerBean(), this.appUserRepository,
            this.jwtUtils));
    http.addFilterBefore(new CustomAuthorizationFilter(jwtUtils),
        UsernamePasswordAuthenticationFilter.class);
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


}
