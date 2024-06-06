package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.PrescriptionDto;
import itacademy.misbackend.entity.helper.Prescription;
import itacademy.misbackend.repo.PrescriptionRepo;
import itacademy.misbackend.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionRepo repo;
    @Override
    public PrescriptionDto create(PrescriptionDto dto) {

        Prescription prescription = Prescription.builder()
                .medication(dto.getMedication())
                .dosage(dto.getDosage())
                .instructions(dto.getInstructions())
         //       .medicalRecord
                .prescriptionDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        repo.save(prescription);

        dto.setId(prescription.getId());
        dto.setPrescriptionDate(prescription.getPrescriptionDate());
        dto.setLastUpdatedAt(prescription.getLastUpdatedAt());
        return dto;
    }

    @Override
    public PrescriptionDto getById(Long id) {
        Prescription prescription = repo.findByDeletedAtIsNullAndId(id);
        if (prescription == null) {
            throw new NullPointerException("Назначение с id " + id + " не найдено!");
        }
        return   PrescriptionDto.builder()
                .id(prescription.getId())
                .medication(prescription.getMedication())
                .dosage(prescription.getDosage())
                .instructions(prescription.getInstructions())
                //       .medicalRecord
                .prescriptionDate(prescription.getPrescriptionDate())
                .lastUpdatedAt(prescription.getLastUpdatedAt())
                .build();
    }

    @Override
    public List<PrescriptionDto> getAll() {
        List<Prescription> recordList = repo.findAllByDeletedAtIsNull();
        if (recordList.isEmpty()) {
            throw new NullPointerException("Назначений нет!");
        }
        List<PrescriptionDto> prescriptionDtoList = new ArrayList<>();
        for (Prescription prescription : recordList) {
            PrescriptionDto prescriptionDto = PrescriptionDto.builder()
                    .id(prescription.getId())
                    .medication(prescription.getMedication())
                    .dosage(prescription.getDosage())
                    .instructions(prescription.getInstructions())
                    //       .medicalRecord
                    .prescriptionDate(prescription.getPrescriptionDate())
                    .lastUpdatedAt(prescription.getLastUpdatedAt())
                    .build();
            prescriptionDtoList.add(prescriptionDto);
        }
        return prescriptionDtoList;
    }

    @Override
    public PrescriptionDto update(Long id, PrescriptionDto dto) {
        Prescription prescription = repo.findByDeletedAtIsNullAndId(id);
        if (prescription == null) {
            throw new NullPointerException("Назначение с id " + id + " не найдено!");
        }
        prescription.setMedication(dto.getMedication());
        prescription.setDosage(dto.getDosage());
        prescription.setInstructions(dto.getInstructions());
        prescription.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));

        repo.save(prescription);

        dto.setLastUpdatedAt(prescription.getLastUpdatedAt());
        return dto;
    }

    @Override
    public String delete(Long id) {
        Prescription prescription = repo.findByDeletedAtIsNullAndId(id);
        if (prescription == null) {
            throw new NullPointerException("Назначение с id " + id + " не найдено!");
        }
        prescription.setDeletedAt(new Timestamp(System.currentTimeMillis()));

        return "Назначение удалено";
    }
}
