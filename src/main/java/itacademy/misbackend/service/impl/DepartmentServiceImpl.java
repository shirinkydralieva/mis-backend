package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.mapper.DepartmentMapper;
import itacademy.misbackend.repo.DepartmentRepo;
import itacademy.misbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo repo;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        log.info("СТАРТ: DepartmentServiceImpl - create() {}", dto);
        Department department = mapper.toEntity(dto);
        department = repo.save(department);
        /*
            Для создания отделения достаточно передать параметры name и description.
            Список врачей в отделении пополняется автоматически при создании врача.
            (Если id отделения верно указан и отделение действительно существует)
         */
        log.info("КОНЕЦ: c - create {} ", department);
        return mapper.toDto(department);
    }

    @Override
    public DepartmentDto getById(Long id) {
        log.info("СТАРТ: DepartmentServiceImpl - getById({})", id);
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (department == null) {
            log.error("Отделение с id " + id + " не найдено!");
            throw new NullPointerException("Отделение не найдено!");
        }
        log.info("КОНЕЦ: DepartmentServiceImpl - getById(). Отделение - {} ", department);
        return mapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAll() {
        log.info("СТАРТ: DepartmentServiceImpl - getAll()");
        var list = repo.findAllByDeletedAtIsNullAndDeletedByIsNull();
        if (list.isEmpty()) {
            log.error("Список отделений пуст");
            throw new NullPointerException("Отделений нет!");
        }
        log.info("КОНЕЦ: DepartmentServiceImpl - getAll()");
        return mapper.toDtoList(list);
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto updateDto) {
        log.info("СТАРТ: DepartmentServiceImpl - update(). Отделение с id {}", id);
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (department != null){
            if (updateDto.getName() != null){
                department.setName(updateDto.getName());
            }
            if (updateDto.getDescription() != null){
                department.setDescription(updateDto.getDescription());
            }
            department = repo.save(department);
            log.info("КОНЕЦ: DepartmentServiceImpl - update(). Отделение обновлено - {}", department);
            return mapper.toDto(department);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        log.info("СТАРТ: DepartmentServiceImpl - delete(). Отделение с id {}", id);
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (department != null) {
            department.setDeletedAt(LocalDateTime.now());
            //department.setDeletedBy(authentication.getName());
            repo.save(department);
            log.info("КОНЕЦ: DepartmentServiceImpl - delete(). Отделение {} удалено", department.getName());
            return "Отделение " + department.getName() + " удалено";
        }
        log.error("Отделение с id " + id + " не найдено!");
        return "Отделение с id " + id + " не найдено";
    }
}
