package itacademy.misbackend.service;

import itacademy.misbackend.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto create(PatientDto patientDto);
    PatientDto getById(Long id);
    List<PatientDto> getAll();
    PatientDto update(Long id, PatientDto patientDto);
    String delete(Long id);
}
