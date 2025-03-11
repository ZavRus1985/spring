package com.ruslan.validation.service.mapper;


import com.ruslan.validation.dto.DepartmentDto;
import com.ruslan.validation.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentDto departmentDto);

    DepartmentDto toDto(Department department);

    void update(@MappingTarget Department department, DepartmentDto departmentDto);
}
