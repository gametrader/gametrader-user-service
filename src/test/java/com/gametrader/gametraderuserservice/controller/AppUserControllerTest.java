package com.gametrader.gametraderuserservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametrader.gametraderuserservice.dto.AppUserDTO;
import com.gametrader.gametraderuserservice.exception.EmailAlreadyTakenException;
import com.gametrader.gametraderuserservice.exception.PasswordsDontMatchException;
import com.gametrader.gametraderuserservice.exception.UsernameAlreadyTakenException;
import com.gametrader.gametraderuserservice.mapper.AppUserMapperImpl;
import com.gametrader.gametraderuserservice.repository.AppUserRepository;
import com.gametrader.gametraderuserservice.repository.UserRoleRepository;
import com.gametrader.gametraderuserservice.service.AppUserService;
import com.gametrader.gametraderuserservice.util.JwtUtils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(properties = {"JWT_ALGORITHM_SECRET=test"})
public class AppUserControllerTest {

    @MockBean
    private JwtUtils jwtUtils;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AppUserService appUserService;
    @Autowired
    private AppUserController appUserController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser() throws Exception {
        AppUserDTO registerRequest =
                AppUserDTO.builder().username("olsza").password("olsza").build();
        Mockito.doNothing().when(appUserService).register(Mockito.any(AppUserDTO.class));
        mockMvc.perform(post("/v1/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());
    }

    /**
     * Method under test: {@link AppUserController#registerUser(AppUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterUser()
            throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of AppUserController.
        //   Add a package-visible constructor or a factory method for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gametrader.gametraderuserservice.exception.EmailAlreadyTakenException: Email jane.doe@example.org is taken
        //       at com.gametrader.gametraderuserservice.service.AppUserService.register(AppUserService.java:41)
        //       at com.gametrader.gametraderuserservice.controller.AppUserController.registerUser(AppUserController.java:37)
        //   In order to prevent registerUser(AppUserDTO)
        //   from throwing EmailAlreadyTakenException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   registerUser(AppUserDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.existsAppUserByEmail((String) any())).thenReturn(true);
        when(appUserRepository.existsAppUserByUsername((String) any())).thenReturn(true);
        AppUserMapperImpl appUserMapper = new AppUserMapperImpl();
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserRoleRepository roleRepository = mock(UserRoleRepository.class);
        AppUserController appUserController = new AppUserController(
                new AppUserService(appUserRepository, appUserMapper, passwordEncoder, roleRepository, new JwtUtils(null)));
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        appUserController.registerUser(appUserDTO);
    }

    /**
     * Method under test: {@link AppUserController#registerUser(AppUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterUser2()
            throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of AppUserController.
        //   Add a package-visible constructor or a factory method for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gametrader.gametraderuserservice.exception.UsernameAlreadyTakenException: Username janedoe is taken
        //       at com.gametrader.gametraderuserservice.service.AppUserService.register(AppUserService.java:45)
        //       at com.gametrader.gametraderuserservice.controller.AppUserController.registerUser(AppUserController.java:37)
        //   In order to prevent registerUser(AppUserDTO)
        //   from throwing UsernameAlreadyTakenException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   registerUser(AppUserDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(appUserRepository.existsAppUserByUsername((String) any())).thenReturn(true);
        AppUserMapperImpl appUserMapper = new AppUserMapperImpl();
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserRoleRepository roleRepository = mock(UserRoleRepository.class);
        AppUserController appUserController = new AppUserController(
                new AppUserService(appUserRepository, appUserMapper, passwordEncoder, roleRepository, new JwtUtils(null)));
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        appUserController.registerUser(appUserDTO);
    }

    /**
     * Method under test: {@link AppUserController#registerUser(AppUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterUser3()
            throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of AppUserController.
        //   Add a package-visible constructor or a factory method for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gametrader.gametraderuserservice.exception.UsernameAlreadyTakenException: Username An error occurred is taken
        //       at com.gametrader.gametraderuserservice.service.AppUserService.register(AppUserService.java:47)
        //       at com.gametrader.gametraderuserservice.controller.AppUserController.registerUser(AppUserController.java:37)
        //   In order to prevent registerUser(AppUserDTO)
        //   from throwing UsernameAlreadyTakenException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   registerUser(AppUserDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(appUserRepository.existsAppUserByUsername((String) any())).thenReturn(false);
        AppUserMapperImpl appUserMapper = new AppUserMapperImpl();
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserRoleRepository roleRepository = mock(UserRoleRepository.class);
        AppUserController appUserController = new AppUserController(
                new AppUserService(appUserRepository, appUserMapper, passwordEncoder, roleRepository, new JwtUtils(null)));
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getMatchingPassword()).thenThrow(new UsernameAlreadyTakenException("An error occurred"));
        when(appUserDTO.getPassword()).thenThrow(new UsernameAlreadyTakenException("An error occurred"));
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        appUserController.registerUser(appUserDTO);
    }

    /**
     * Method under test: {@link AppUserController#registerUser(AppUserDTO)}
     */
    @Test
    void testRegisterUser4()
            throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of AppUserController.
        //   Add a package-visible constructor or a factory method for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008

        AppUserService appUserService = mock(AppUserService.class);
        doNothing().when(appUserService).register((AppUserDTO) any());
        AppUserController appUserController = new AppUserController(appUserService);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getMatchingPassword()).thenThrow(new UsernameAlreadyTakenException("An error occurred"));
        when(appUserDTO.getPassword()).thenThrow(new UsernameAlreadyTakenException("An error occurred"));
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        ResponseEntity<?> actualRegisterUserResult = appUserController.registerUser(appUserDTO);
        assertNull(actualRegisterUserResult.getBody());
        assertEquals(HttpStatus.CREATED, actualRegisterUserResult.getStatusCode());
        assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
        verify(appUserService).register((AppUserDTO) any());
    }

    /**
     * Method under test: {@link AppUserController#refreshToken(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
        //       at org.apache.catalina.connector.Response.setContentType(Response.java:741)
        //       at com.gametrader.gametraderuserservice.controller.AppUserController.refreshToken(AppUserController.java:45)
        //   In order to prevent refreshToken(HttpServletRequest, HttpServletResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   refreshToken(HttpServletRequest, HttpServletResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        AppUserMapperImpl appUserMapper = new AppUserMapperImpl();
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserRoleRepository roleRepository = mock(UserRoleRepository.class);
        AppUserController appUserController = new AppUserController(
                new AppUserService(appUserRepository, appUserMapper, passwordEncoder, roleRepository, new JwtUtils(null)));
        HttpServletRequestWrapper request = new HttpServletRequestWrapper(
                new HttpServletRequestWrapper(new HttpServletRequestWrapper(
                        new HttpServletRequestWrapper(new HttpServletRequestWrapper(new MockHttpServletRequest())))));
        appUserController.refreshToken(request, new Response());
    }

    @Test
    void refreshToken() {
    }
}