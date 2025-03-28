package com.ruslan.springsecurity.service.mapper;

import com.ruslan.springsecurity.dto.UserDto;
import com.ruslan.springsecurity.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring", imports = PasswordEncoder.class)
public interface UserMapper {

//    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.getPassword()))")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    ApplicationUser toEntity(UserDto userDto);


    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDto toDto(ApplicationUser applicationUser);

}
