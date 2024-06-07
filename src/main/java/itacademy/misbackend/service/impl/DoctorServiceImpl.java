package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.entity.Doctor;
import itacademy.misbackend.mapper.DoctorMapper;
import itacademy.misbackend.repo.DepartmentRepo;
import itacademy.misbackend.repo.DoctorRepo;
import itacademy.misbackend.repo.UserRepo;
import itacademy.misbackend.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;
    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;

    @Override
    public DoctorDto create(DoctorDto dto) {
        log.info("СТАРТ: DoctorServiceImpl - create() {}", dto);

        Doctor doctor = doctorMapper.toEntity(dto);
        doctor.setDepartment(departmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getDepartmentId()));
        doctor.setUser(userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getUserId())); //!
        doctor = doctorRepo.save(doctor);
        log.info("КОНЕЦ: DoctorServiceImpl - create {} ", dto);
        return doctorMapper.toDto(doctor);
    }

    @Override
    public DoctorDto getById(Long id) {
        log.info("СТАРТ: DoctorServiceImpl - getById({})", id);
        Doctor doctor = doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (doctor == null) {
            log.error("Врач с id " + id + " не найден!");
            throw new NullPointerException("Врач не найден!");
        }
        log.info("КОНЕЦ: DoctorServiceImpl - getById(). Врач - {} ", doctor);
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> getAll() {
        log.info("СТАРТ: DoctorServiceImpl - getAll()");
        var doctorList = doctorRepo.findAllByDeletedAtIsNullAndDeletedByIsNull();
        if (doctorList == null) {
            log.error("Список Врачей пуст!");
            throw new NullPointerException("Врачей нет!");
        }
        log.info("КОНЕЦ: DoctorServiceImpl - getAll()");
        return doctorMapper.toDtoList(doctorList);
    }

    @Override
    public DoctorDto update(Long id, DoctorDto updateDto) {
        log.info("СТАРТ: DoctorServiceImpl - update(). Врач с id {}", id);
        Doctor doctor = doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (doctor != null) {
            if (updateDto.getFirstName() != null) {
                doctor.setFirstName(updateDto.getFirstName());
            }
            if (updateDto.getLastName() != null) {
                doctor.setLastName(updateDto.getLastName());
            }
            if (updateDto.getPatronymic() != null) {
                doctor.setPatronymic(updateDto.getPatronymic());
            }
            if (updateDto.getSpecialization() != null) {
                doctor.setSpecialization(updateDto.getSpecialization());
            }
            if (updateDto.getQualification() != null) {
                doctor.setQualification(updateDto.getQualification());
            }
            if (updateDto.getPhoneNumber() != null) {
                doctor.setPhoneNumber(updateDto.getPhoneNumber());
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
            /* if (updateDto.getUserId() != null) {
                doctor.setUser(...);
            }*/
            doctor = doctorRepo.save(doctor);
            log.info("КОНЕЦ: DoctorServiceImpl - update(). Записи о Враче обновлены - {}", updateDto);
            return doctorMapper.toDto(doctor);
        }

        return null;
    }

    @Override
    public String delete(Long id) {
        log.info("СТАРТ: DoctorServiceImpl - delete(). Врач с id {}", id);
        Doctor doctor = doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (doctor != null) {
            doctor.setDeletedAt(LocalDateTime.now());
            //doctor.setDeletedBy(authentication.getName());
            doctorRepo.save(doctor);
            log.info("КОНЕЦ: DoctorServiceImpl - delete(). Врач (id {}) удален", id);
            return "Врач с id " + id + " удален";
        }
        log.error("Врач с id " + id + " не найден!");
        return "Врач с id " + id + " не найден";
    }
}
