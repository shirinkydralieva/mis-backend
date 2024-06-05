package itacademy.misbackend.mapper.impl;

import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.mapper.DepartmentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department department) {
        DepartmentDto dto = new DepartmentDto(department.getId(), department.getName(), department.getDescription());
        if (department.getDoctors() != null) {
            dto.setDoctors(new DoctorMapperImpl().toDtoList(department.getDoctors()));
        }
        return dto;
    }

    @Override
    public Department toEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        return department;
    }

    @Override
    public List<DepartmentDto> toDtoList(List<Department> department) {
        var dtos = new ArrayList<DepartmentDto>();
        for (Department d : department) {
            dtos.add(toDto(d));
        }
        return dtos;
    }
}
