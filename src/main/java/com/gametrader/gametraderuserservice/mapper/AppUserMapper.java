package com.gametrader.gametraderuserservice.mapper;


import com.gametrader.gametraderuserservice.dto.AppUserDTO;
import com.gametrader.gametraderuserservice.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserDTO toDto(AppUser user);

    AppUser toEntity(AppUserDTO registerUser);
}
