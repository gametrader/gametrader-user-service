package com.gametrader.gametraderuserservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gametrader.gametraderuserservice.model.AppUser;
import com.gametrader.gametraderuserservice.model.UserRole;
import com.gametrader.gametraderuserservice.repository.AppUserRepository;
import com.gametrader.gametraderuserservice.util.Gender;
import com.gametrader.gametraderuserservice.util.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.appUserRepository).findAppUserByUsername((String) any());
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
        //       at org.springframework.util.Assert.isTrue(Assert.java:121)
        //       at org.springframework.security.core.userdetails.User.<init>(User.java:110)
        //       at org.springframework.security.core.userdetails.User.<init>(User.java:87)
        //       at com.gametrader.gametraderuserservice.service.UserDetailsServiceImpl.loadUserByUsername(UserDetailsServiceImpl.java:30)
        //   In order to prevent loadUserByUsername(String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   loadUserByUsername(String).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = mock(AppUser.class);
        when(appUser.getPassword()).thenReturn("iloveyou");
        when(appUser.getUsername()).thenReturn("");
        when(appUser.getUserRoles()).thenReturn(new HashSet<>());
        doNothing().when(appUser).setBirthdate((LocalDate) any());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setGender((Gender) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setUserRoles((java.util.Set<UserRole>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(ofResult);
        this.userDetailsServiceImpl.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        UserRole userRole = new UserRole();
        userRole.setId(123L);
        userRole.setRole(Role.ADMIN);

        HashSet<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);
        AppUser appUser = mock(AppUser.class);
        when(appUser.getPassword()).thenReturn("iloveyou");
        when(appUser.getUsername()).thenReturn("janedoe");
        when(appUser.getUserRoles()).thenReturn(userRoleSet);
        doNothing().when(appUser).setBirthdate((LocalDate) any());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setGender((Gender) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setUserRoles((Set<UserRole>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.appUserRepository).findAppUserByUsername((String) any());
        verify(appUser).getPassword();
        verify(appUser).getUsername();
        verify(appUser).getUserRoles();
        verify(appUser).setBirthdate((LocalDate) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setGender((Gender) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setUserRoles((Set<UserRole>) any());
        verify(appUser).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        when(this.appUserRepository.findAppUserByUsername((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        when(appUser.getPassword()).thenReturn("iloveyou");
        when(appUser.getUsername()).thenReturn("janedoe");
        when(appUser.getUserRoles()).thenReturn(new HashSet<>());
        doNothing().when(appUser).setBirthdate((LocalDate) any());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setGender((Gender) any());
        doNothing().when(appUser).setId((Long) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setUserRoles((java.util.Set<UserRole>) any());
        doNothing().when(appUser).setUsername((String) any());
        appUser.setBirthdate(LocalDate.ofEpochDay(1L));
        appUser.setEmail("jane.doe@example.org");
        appUser.setGender(Gender.MALE);
        appUser.setId(123L);
        appUser.setPassword("iloveyou");
        appUser.setUserRoles(new HashSet<>());
        appUser.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> this.userDetailsServiceImpl.loadUserByUsername("janedoe"));
        verify(this.appUserRepository).findAppUserByUsername((String) any());
        verify(appUser).setBirthdate((LocalDate) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setGender((Gender) any());
        verify(appUser).setId((Long) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setUserRoles((java.util.Set<UserRole>) any());
        verify(appUser).setUsername((String) any());
    }
}

