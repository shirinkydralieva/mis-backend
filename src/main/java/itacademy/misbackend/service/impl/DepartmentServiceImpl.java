package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.mapper.DepartmentMapper;
import itacademy.misbackend.repo.DepartmentRepo;
import itacademy.misbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo repo;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        Department department = mapper.toEntity(dto);
        department = repo.save(department);
        /*
            Для создания отделения достаточно передать параметры name и description.
            Список врачей в отделении пополняется автоматически при создании врача.
            (Если id отделения верно указан и отделение действительно существует)
         */
        return mapper.toDto(department);
    }

    @Override
    public DepartmentDto getById(Long id) {
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        return mapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAll() {
        return mapper.toDtoList(repo.findAllByDeletedAtIsNullAndDeletedByIsNull());
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto updateDto) {
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (department != null){
            if (updateDto.getName() != null){
                department.setName(updateDto.getName());
            }
            if (updateDto.getDescription() != null){
                department.setDescription(updateDto.getDescription());
            }
            department = repo.save(department);
            return mapper.toDto(department);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        Department department = repo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (department != null) {
            department.setDeletedAt(LocalDateTime.now());
            //department.setDeletedBy(authentication.getName());
            return "Отделение " + department.getName() + " удалено";
        }
        return "Отделение с id " + id + " не найдено";
    }
}
