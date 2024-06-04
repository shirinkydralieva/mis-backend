package itacademy.misbackend.mapper.impl;

import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.entity.Department;
import itacademy.misbackend.entity.Doctor;
import itacademy.misbackend.mapper.DoctorMapper;
import itacademy.misbackend.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDto toDto(Doctor doctor) {
        DoctorDto dto = DoctorDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .phoneNumber(doctor.getPhoneNumber())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .build();
        if (doctor.getAppointments() != null) {
            dto.setAppointments(new AppointmentMapperImpl().toDtoList(doctor.getAppointments()));
        }
        if (doctor.getDepartment() != null) {
            dto.setDepartmentId(doctor.getDepartment().getId());
        }
        if (doctor.getUser() != null) {
            dto.setUserId(doctor.getUser().getId());
        }
        return dto;
    }

    @Override
    public Doctor toEntity(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .fullName(doctorDto.getFullName())
                .phoneNumber(doctorDto.getPhoneNumber())
                .specialization(doctorDto.getSpecialization())
                .qualification(doctorDto.getQualification())
                .build();
        return doctor;
    }

    @Override
    public List<DoctorDto> toDtoList(List<Doctor> doctor) {
        var dtos = new ArrayList<DoctorDto>();
        for (Doctor d : doctor) {
            dtos.add(toDto(d));
        }
        return dtos;
    }
}
