package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.mapper.AppointmentMapper;
import itacademy.misbackend.mapper.MedicalRecordMapper;
import itacademy.misbackend.repo.*;
import itacademy.misbackend.service.AppointmentService;
import itacademy.misbackend.service.MedCardService;
import itacademy.misbackend.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordServiceImpl implements MedicalRecordService {
      private final MedicalRecordRepo repo;
      private final MedicalRecordMapper mapper;
      private final AppointmentRepo appointmentRepo;
      private final MedCardRepo medCardRepo;
      private final DiagnosisRepo diagnosisRepo;
      private final PrescriptionRepo prescriptionRepo;
    @Override
    public MedicalRecordDto create(MedicalRecordDto recordDto) {
        log.info("СТАРТ: MedicalRecordServiceImpl - create() {}", recordDto);
     //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     //   String username = authentication.getName();

        MedicalRecord record = mapper.toEntity(recordDto);
        if (recordDto.getDiagnosis() != null) {
            diagnosisRepo.save(record.getDiagnosis());
            log.info("Сохранение диагноза");
        }
        if (recordDto.getPrescription() != null) {
            prescriptionRepo.save(record.getPrescription());
            log.info("Сохранение назначения");
        }

        record.setAppointment(appointmentRepo
                .findByDeletedAtIsNullAndDeletedByIsNullAndId(
                        recordDto.getAppointmentId() ) );
        record.setMedCard(medCardRepo.findByDeletedAtIsNullAndId(recordDto.getMedCardId()));
        record.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    //    record.setCreatedBy(username);
        record.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
     //   record.setLastUpdatedBy(username);

        repo.save(record);
        log.info("КОНЕЦ: MedicalRecordServiceImpl - create {} ", record);
        return mapper.toDto(record);
    }

    @Override
    public MedicalRecordDto getById(Long id) {
        log.info("СТАРТ: MedicalRecordServiceImpl - getById({})", id);
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            log.error("Медицинская запись с id " + id + " не найдена!");
            throw new NullPointerException("Медицинская запись не найдена!");
        }
        log.info("КОНЕЦ: MedicalRecordServiceImpl - getById(). Медицинская запись - {} ", record);
        return  mapper.toDto(record);
    }

    @Override
    public List<MedicalRecordDto> getAll() {
        log.info("СТАРТ: MedicalRecordServiceImpl - getAll()");
        List<MedicalRecord> recordList = repo.findAllByDeletedAtIsNull();
        if (recordList.isEmpty()) {
            log.error("Записей нет!");
            throw new NullPointerException("Записей нет!");
        }
        log.info("КОНЕЦ: MedicalRecordServiceImpl - getAll()");
        return mapper.toDtoList(recordList);
    }

    @Override
    public MedicalRecordDto update(Long id, MedicalRecordDto dto) {
        log.info("СТАРТ: MedicalRecordServiceImpl - update(). Мед запись с id {}", id);

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();*/

        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            log.error("Медицинская запись с id " + id + " не найдена!");
            throw new NullPointerException("Медицинская запись не найдена!");
        }
        log.info("Запись. Исходные данные - {}", record);
        record = mapper.toEntity(dto);
        record.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
       // record.setLastUpdatedBy(username);

        repo.save(record);

        dto.setLastUpdatedAt(record.getLastUpdatedAt());
        dto.setLastUpdatedBy(record.getLastUpdatedBy());
        log.info("КОНЕЦ: MedicalRecordServiceImpl - update(). Обновленная запись - {}", record);
        return dto;
    }

    @Override
    public String delete(Long id) {
        log.info("СТАРТ: MedicalRecordServiceImpl - delete(). Мед запись с id {}", id);
        //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //   String username = authentication.getName();

        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            log.error("Медицинская запись с id " + id + " не найдена!");
            throw new NullPointerException("Медицинская запись не найдена!");
        }
        record.setDeletedAt(new Timestamp(System.currentTimeMillis()));
      //  record.setDeletedBy(username);
        repo.save(record);
        log.info("КОНЕЦ: ServiceTypeServiceImpl - delete(). Мед запись (id {}) удалена", id);
        return "Медицинская запись удалена";
    }
}
