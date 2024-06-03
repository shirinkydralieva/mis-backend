package itacademy.misbackend.service;

import itacademy.misbackend.dto.DiagnosisDto;

import java.util.List;

public interface DiagnosisService {
    DiagnosisDto create (DiagnosisDto dto);

    DiagnosisDto getById (Long id);

    List<DiagnosisDto> getAll();

    DiagnosisDto update (Long id, DiagnosisDto dto);

    String delete (Long id);
}
