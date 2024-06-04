package itacademy.misbackend.service;

import itacademy.misbackend.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto create(DoctorDto dto);
    DoctorDto getById(Long id);
    List<DoctorDto> getAll();
    DoctorDto update(Long id, DoctorDto updateDto);
    String delete(Long id);
}
