package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.DiagnosisDto;
import itacademy.misbackend.entity.Diagnosis;
import itacademy.misbackend.repo.DiagnosisRepo;
import itacademy.misbackend.service.DiagnosisService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private DiagnosisRepo repo;
    @Override
    public DiagnosisDto create(DiagnosisDto dto) {
        Diagnosis diagnosis = Diagnosis.builder()
                .description(dto.getDescription())
                .code(dto.getCode())
                //       .medicalRecord
                .diagnosisDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        repo.save(diagnosis);

        dto.setId(diagnosis.getId());
        dto.setDiagnosisDate(diagnosis.getDiagnosisDate());
        dto.setLastUpdatedAt(diagnosis.getLastUpdatedAt());
        return dto;
    }

    @Override
    public DiagnosisDto getById(Long id) {
        Diagnosis diagnosis = repo.findByDeletedAtIsNullAndId(id);
        if (diagnosis == null) {
            throw new NullPointerException("Диагноз с id " + id + " не найден!");
        }
        return   DiagnosisDto.builder()
                .id(diagnosis.getId())
                .description(diagnosis.getDescription())
                .code(diagnosis.getCode())
                //       .medicalRecord
                .diagnosisDate(diagnosis.getDiagnosisDate())
                .lastUpdatedAt(diagnosis.getLastUpdatedAt())
                .build();
    }

    @Override
    public List<DiagnosisDto> getAll() {
        List<Diagnosis> diagnosisList = repo.findAllByDeletedAtIsNull();
        if (diagnosisList.isEmpty()) {
            throw new NullPointerException("Диагнозов нет!");
        }
        List<DiagnosisDto> diagnosisDtoList = new ArrayList<>();
        for (Diagnosis diagnosis : diagnosisList) {
            DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                    .id(diagnosis.getId())
                    .description(diagnosis.getDescription())
                    .code(diagnosis.getCode())
                    //       .medicalRecord
                    .diagnosisDate(diagnosis.getDiagnosisDate())
                    .lastUpdatedAt(diagnosis.getLastUpdatedAt())
                    .build();
            diagnosisDtoList.add(diagnosisDto);
        }
        return diagnosisDtoList;
    }

    @Override
    public DiagnosisDto update(Long id, DiagnosisDto dto) {
        Diagnosis diagnosis = repo.findByDeletedAtIsNullAndId(id);
        if (diagnosis == null) {
            throw new NullPointerException("Диагноз с id " + id + " не найден!");
        }
        diagnosis.setDescription(dto.getDescription());
        diagnosis.setCode(dto.getCode());
        diagnosis.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));

        repo.save(diagnosis);

        dto.setLastUpdatedAt(diagnosis.getLastUpdatedAt());
        return dto;
    }

    @Override
    public String delete(Long id) {
        Diagnosis diagnosis = repo.findByDeletedAtIsNullAndId(id);
        if (diagnosis == null) {
            throw new NullPointerException("Диагноз с id " + id + " не найден!");
        }
        diagnosis.setDeletedAt(new Timestamp(System.currentTimeMillis()));

        return "Диагноз удален";
    }
}
