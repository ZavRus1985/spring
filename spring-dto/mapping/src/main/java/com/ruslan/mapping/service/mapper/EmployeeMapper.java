package com.ruslan.mapping.service.mapper;

import com.ruslan.mapping.dto.EmployeeDto;
import com.ruslan.mapping.entity.Department;
import com.ruslan.mapping.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", imports = Department.class)
public interface EmployeeMapper {


    @Mapping(target = "department",
            expression = "java(new Department(employeeDto.getDepartmentName()))")
    Employee toEntity(EmployeeDto employeeDto);


    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    EmployeeDto toDto(Employee employee);

    void update(@MappingTarget Employee employee, EmployeeDto dto);
}
