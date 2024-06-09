package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.entity.Doctor;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.mapper.DoctorMapper;
import itacademy.misbackend.repo.DepartmentRepo;
import itacademy.misbackend.repo.DoctorRepo;
import itacademy.misbackend.repo.UserRepo;
import itacademy.misbackend.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;
    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;

    @Override
    public DoctorDto save(DoctorDto dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        if (departmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getDepartmentId()) == null) {
            throw new NotFoundException("Отделение с id " + dto.getDepartmentId() + " не найдено");
        }
        if (userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getUserId()) == null) {
            throw new NotFoundException("Пользователь с id " + dto.getUserId() + " не найден");
        }
        doctor.setDepartment(departmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getDepartmentId()));
        doctor.setUser(userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getUserId()));
        doctor = doctorRepo.save(doctor);
        return doctorMapper.toDto(doctor);
    }

    @Override
    public DoctorDto getById(Long id) {
        return doctorMapper.toDto(doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id));
    }

    @Override
    public List<DoctorDto> getAll() {
        return doctorMapper.toDtoList(doctorRepo.findAllByDeletedAtIsNullAndDeletedByIsNull());
    }

    @Override
    public DoctorDto update(Long id, DoctorDto updateDto) {
        Doctor doctor = doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (doctor != null) {
            if (updateDto.getSpecialization() != null) {
                doctor.setSpecialization(updateDto.getSpecialization());
            }
            if (updateDto.getDepartmentId() != null) {
                Department department = departmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(updateDto.getDepartmentId());
                if (department != null) {
                    doctor.setDepartment(department);
                    department.getDoctors().add(doctor);
                    departmentRepo.save(department);
                } else {
                    throw new EntityNotFoundException("Отделение с id " + updateDto.getDepartmentId() + " не найдено");
                }
            }
            doctor = doctorRepo.save(doctor);
            return doctorMapper.toDto(doctor);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        Doctor doctor = doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (doctor != null) {
            doctor.setDeletedAt(LocalDateTime.now());
            doctor.setDeletedBy(authentication.getName());
            return "Врач с id " + id + " удален";
        }
        return "Врач с id " + id + " не найден";
    }
}
