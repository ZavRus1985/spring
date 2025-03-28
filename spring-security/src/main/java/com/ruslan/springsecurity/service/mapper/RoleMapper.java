package com.ruslan.springsecurity.service.mapper;

import com.ruslan.springsecurity.dto.RoleDto;
import com.ruslan.springsecurity.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
