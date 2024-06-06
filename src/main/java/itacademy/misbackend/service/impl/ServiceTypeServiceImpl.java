package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.dto.ServiceTypeDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.entity.ServiceType;
import itacademy.misbackend.entity.helper.Department;
import itacademy.misbackend.repo.DepartmentRepo;
import itacademy.misbackend.repo.ServiceTypeRepo;
import itacademy.misbackend.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepo repo;
    private final DepartmentRepo departmentRepo;
    @Override
    public ServiceTypeDto create(ServiceTypeDto dto) {
        ServiceType serviceType = ServiceType.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .department(departmentRepo
                        .findByDeletedAtIsNullAndDeletedByIsNullAndId(dto.getDepartmentId() ) )
                .build();

        repo.save(serviceType);
        dto.setId(serviceType.getId());
        return dto;
    }

    @Override
    public ServiceTypeDto getById(Long id) {
        ServiceType serviceType = repo.findByDeletedAtIsNullAndId(id);
        return ServiceTypeDto.builder()
                .id(serviceType.getId())
                .name(serviceType.getName())
                .description(serviceType.getDescription())
                .price(serviceType.getPrice())
                .departmentId(serviceType.getDepartment().getId() )
                .build();
    }

    @Override
    public List<ServiceTypeDto> getAll() {
        List<ServiceType> serviceList = repo.findAllByDeletedAtIsNull();
        var dtoList = new ArrayList<ServiceTypeDto>();
        for (ServiceType serviceType : serviceList) {
            ServiceTypeDto dto = ServiceTypeDto.builder()
                    .id(serviceType.getId())
                    .name(serviceType.getName())
                    .description(serviceType.getDescription())
                    .price(serviceType.getPrice())
                    .departmentId(serviceType.getDepartment().getId() )
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ServiceTypeDto update(Long id, ServiceTypeDto updateDto) {
        ServiceType serviceType = repo.findByDeletedAtIsNullAndId(id);
        if (serviceType != null){
            if (updateDto.getName() != null){
                serviceType.setName(updateDto.getName());
            }
            if (updateDto.getDescription() != null){
                serviceType.setDescription(updateDto.getDescription());
            }
            if (updateDto.getPrice() != null){
                serviceType.setPrice(updateDto.getPrice());
            }
            if (updateDto.getDepartmentId() != null){
                serviceType.setDepartment(departmentRepo
                        .findByDeletedAtIsNullAndDeletedByIsNullAndId(updateDto.getDepartmentId() ) );
            }
            serviceType = repo.save(serviceType);
            return  ServiceTypeDto.builder()
                    .id(serviceType.getId())
                    .name(serviceType.getName())
                    .description(serviceType.getDescription())
                    .price(serviceType.getPrice())
                    .departmentId(serviceType.getDepartment().getId() )
                    .build();
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        ServiceType serviceType = repo.findByDeletedAtIsNullAndId(id);
        if (serviceType == null) {
            throw new NullPointerException("Услуга с id " + id + " не найдена!");
        }
        serviceType.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return "Услуга удалена";
    }

}
