package com.gametrader.gametraderuserservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.gametrader.gametraderuserservice.util.Gender;
import com.gametrader.gametraderuserservice.util.JwtUtils;
import com.gametrader.gametraderuserservice.util.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class})
@ExtendWith(SpringExtension.class)
class AppUserServiceTest {
    @MockBean
    private AppUserMapper appUserMapper;

    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRoleRepository userRoleRepository;

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(true);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(true);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(EmailAlreadyTakenException.class, () -> this.appUserService.register(appUserDTO));
        verify(this.appUserRepository).existsAppUserByEmail((String) any());
        verify(appUserDTO, atLeast(1)).getEmail();
    }

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister2() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(true);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(true);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getEmail()).thenThrow(new EmailAlreadyTakenException("An error occurred"));
        assertThrows(EmailAlreadyTakenException.class, () -> this.appUserService.register(appUserDTO));
        verify(appUserDTO).getEmail();
    }

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister3() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(true);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(UsernameAlreadyTakenException.class, () -> this.appUserService.register(appUserDTO));
        verify(this.appUserRepository).existsAppUserByEmail((String) any());
        verify(this.appUserRepository).existsAppUserByUsername((String) any());
        verify(appUserDTO).getEmail();
        verify(appUserDTO, atLeast(1)).getUsername();
    }

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister4() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        UserRole userRole = new UserRole();
        userRole.setId(123L);
        userRole.setRole(Role.ADMIN);
        when(this.userRoleRepository.getByRole((Role) any())).thenReturn(userRole);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(false);

        AppUser appUser1 = new AppUser();
        appUser1.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setGender(Gender.MALE);
        appUser1.setId(123L);
        appUser1.setPassword("iloveyou");
        appUser1.setUserRoles(new HashSet<>());
        appUser1.setUsername("janedoe");
        when(this.appUserMapper.toDto((AppUser) any())).thenReturn(mock(AppUserDTO.class));
        when(this.appUserMapper.toEntity((AppUserDTO) any())).thenReturn(appUser1);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getMatchingPassword()).thenReturn("iloveyou");
        when(appUserDTO.getPassword()).thenReturn("iloveyou");
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        this.appUserService.register(appUserDTO);
        verify(this.userRoleRepository).getByRole((Role) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.appUserRepository).existsAppUserByEmail((String) any());
        verify(this.appUserRepository).existsAppUserByUsername((String) any());
        verify(this.appUserRepository).save((AppUser) any());
        verify(this.appUserMapper).toDto((AppUser) any());
        verify(this.appUserMapper).toEntity((AppUserDTO) any());
        verify(appUserDTO).getEmail();
        verify(appUserDTO).getMatchingPassword();
        verify(appUserDTO, atLeast(1)).getPassword();
        verify(appUserDTO).getUsername();
    }

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister5() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        UserRole userRole = new UserRole();
        userRole.setId(123L);
        userRole.setRole(Role.ADMIN);
        when(this.userRoleRepository.getByRole((Role) any())).thenReturn(userRole);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(false);

        AppUser appUser1 = new AppUser();
        appUser1.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setGender(Gender.MALE);
        appUser1.setId(123L);
        appUser1.setPassword("iloveyou");
        appUser1.setUserRoles(new HashSet<>());
        appUser1.setUsername("janedoe");
        when(this.appUserMapper.toDto((AppUser) any())).thenThrow(new EmailAlreadyTakenException("An error occurred"));
        when(this.appUserMapper.toEntity((AppUserDTO) any())).thenReturn(appUser1);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getMatchingPassword()).thenReturn("iloveyou");
        when(appUserDTO.getPassword()).thenReturn("iloveyou");
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(EmailAlreadyTakenException.class, () -> this.appUserService.register(appUserDTO));
        verify(this.userRoleRepository).getByRole((Role) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.appUserRepository).existsAppUserByEmail((String) any());
        verify(this.appUserRepository).existsAppUserByUsername((String) any());
        verify(this.appUserRepository).save((AppUser) any());
        verify(this.appUserMapper).toDto((AppUser) any());
        verify(this.appUserMapper).toEntity((AppUserDTO) any());
        verify(appUserDTO).getEmail();
        verify(appUserDTO).getMatchingPassword();
        verify(appUserDTO, atLeast(1)).getPassword();
        verify(appUserDTO).getUsername();
    }

    /**
     * Method under test: {@link AppUserService#register(AppUserDTO)}
     */
    @Test
    void testRegister6() throws EmailAlreadyTakenException, PasswordsDontMatchException, UsernameAlreadyTakenException {
        UserRole userRole = new UserRole();
        userRole.setId(123L);
        userRole.setRole(Role.ADMIN);
        when(this.userRoleRepository.getByRole((Role) any())).thenReturn(userRole);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(this.appUserRepository.existsAppUserByEmail((String) any())).thenReturn(false);
        when(this.appUserRepository.existsAppUserByUsername((String) any())).thenReturn(false);

        AppUser appUser1 = new AppUser();
        appUser1.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setGender(Gender.MALE);
        appUser1.setId(123L);
        appUser1.setPassword("iloveyou");
        appUser1.setUserRoles(new HashSet<>());
        appUser1.setUsername("janedoe");
        when(this.appUserMapper.toDto((AppUser) any())).thenReturn(mock(AppUserDTO.class));
        when(this.appUserMapper.toEntity((AppUserDTO) any())).thenReturn(appUser1);
        AppUserDTO appUserDTO = mock(AppUserDTO.class);
        when(appUserDTO.getMatchingPassword()).thenReturn("foo");
        when(appUserDTO.getPassword()).thenReturn("iloveyou");
        when(appUserDTO.getUsername()).thenReturn("janedoe");
        when(appUserDTO.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(PasswordsDontMatchException.class, () -> this.appUserService.register(appUserDTO));
        verify(this.appUserRepository).existsAppUserByEmail((String) any());
        verify(this.appUserRepository).existsAppUserByUsername((String) any());
        verify(appUserDTO).getEmail();
        verify(appUserDTO).getMatchingPassword();
        verify(appUserDTO).getPassword();
        verify(appUserDTO).getUsername();
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.gametrader.gametraderuserservice.service.AppUserService.refreshToken(AppUserService.java:66)
        //   In order to prevent refreshToken(String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   refreshToken(String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.jwtUtils.decodeJwt((String) any())).thenReturn(null);
        this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123");
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    void testRefreshToken2() {
        when(this.jwtUtils.decodeJwt((String) any())).thenThrow(new EmailAlreadyTakenException("An error occurred"));
        assertThrows(EmailAlreadyTakenException.class,
                () -> this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123"));
        verify(this.jwtUtils).decodeJwt((String) any());
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    void testRefreshToken3() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("Hello from the Dreaming Spires");
        when(this.jwtUtils.createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any()))
                .thenReturn("ABC123");
        when(this.jwtUtils.decodeJwt((String) any())).thenReturn(decodedJWT);

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(ofResult);
        Map<String, String> actualRefreshTokenResult = this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123");
        assertEquals(2, actualRefreshTokenResult.size());
        assertEquals("ABC123", actualRefreshTokenResult.get("access_token"));
        assertEquals("ABC123", actualRefreshTokenResult.get("refresh_token"));
        verify(this.jwtUtils).decodeJwt((String) any());
        verify(this.jwtUtils).createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any());
        verify(decodedJWT).getSubject();
        verify(this.appUserRepository).findAppUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    void testRefreshToken4() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("Hello from the Dreaming Spires");
        when(this.jwtUtils.createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any()))
                .thenThrow(new EmailAlreadyTakenException("An error occurred"));
        when(this.jwtUtils.decodeJwt((String) any())).thenReturn(decodedJWT);

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(ofResult);
        assertThrows(EmailAlreadyTakenException.class,
                () -> this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123"));
        verify(this.jwtUtils).decodeJwt((String) any());
        verify(this.jwtUtils).createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any());
        verify(decodedJWT).getSubject();
        verify(this.appUserRepository).findAppUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    void testRefreshToken5() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("Hello from the Dreaming Spires");
        when(this.jwtUtils.createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any()))
                .thenReturn("ABC123");
        when(this.jwtUtils.decodeJwt((String) any())).thenReturn(decodedJWT);

        UserRole userRole = new UserRole();
        userRole.setId(123L);
        userRole.setRole(Role.ADMIN);

        HashSet<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        AppUser appUser = new AppUser();
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(userRoleSet);
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(ofResult);
        Map<String, String> actualRefreshTokenResult = this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123");
        assertEquals(2, actualRefreshTokenResult.size());
        assertEquals("ABC123", actualRefreshTokenResult.get("access_token"));
        assertEquals("ABC123", actualRefreshTokenResult.get("refresh_token"));
        verify(this.jwtUtils).decodeJwt((String) any());
        verify(this.jwtUtils).createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any());
        verify(decodedJWT).getSubject();
        verify(this.appUserRepository).findAppUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AppUserService#refreshToken(String, String, String)}
     */
    @Test
    void testRefreshToken6() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("Hello from the Dreaming Spires");
        when(this.jwtUtils.createAccessToken((String) any(), (String) any(), (java.util.List<Object>) any()))
                .thenReturn("ABC123");
        when(this.jwtUtils.decodeJwt((String) any())).thenReturn(decodedJWT);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> this.appUserService.refreshToken("JaneDoe", "Issuer", "ABC123"));
        verify(this.jwtUtils).decodeJwt((String) any());
        verify(decodedJWT).getSubject();
        verify(this.appUserRepository).findAppUserByUsername((String) any());
    }
}

