package com.ruslan.mapping.service.mapper;

import com.ruslan.mapping.dto.DepartmentDto;
import com.ruslan.mapping.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentDto departmentDto);

    DepartmentDto toDto(Department department);

    void update(@MappingTarget Department department, DepartmentDto departmentDto);
}
