package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.mapper.MedicalRecordMapper;
import itacademy.misbackend.repo.*;
import itacademy.misbackend.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordServiceImpl implements MedicalRecordService {
      private final MedicalRecordRepo repo;
      private final MedicalRecordMapper mapper;
      private final AppointmentRepo appointmentRepo;
      private final MedCardRepo medCardRepo;

    //@Transactional
      @Override
    public MedicalRecordDto create(MedicalRecordDto recordDto) {
        log.info("СТАРТ: MedicalRecordServiceImpl - create() {}", recordDto);
     //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MedicalRecord record = mapper.toEntity(recordDto);
        record.setAppointment(appointmentRepo
                .findByDeletedAtIsNullAndDeletedByIsNullAndId(
                        recordDto.getAppointmentId() ) );
        record.setMedCard(medCardRepo.findByDeletedAtIsNullAndId(
                record.getAppointment().getPatient().getId()));
        record.setCreatedAt(LocalDateTime.now());
    //    record.setCreatedBy(authentication.getName());
        record.setLastUpdatedAt(LocalDateTime.now());
     //   record.setLastUpdatedBy(authentication.getName());

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
        List<MedicalRecord> records = repo.findAllByDeletedAtIsNull();
        if (records.isEmpty()) {
            log.error("Записей нет!");
            throw new NullPointerException("Записей нет!");
        }
        log.info("КОНЕЦ: MedicalRecordServiceImpl - getAll()");
        return mapper.toDtoList(records);
    }

    @Override
    public MedicalRecordDto update(Long id, MedicalRecordDto updateDto) {
        log.info("СТАРТ: MedicalRecordServiceImpl - update(). Мед запись с id {}", id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            log.error("Медицинская запись с id " + id + " не найдена!");
            throw new NullPointerException("Медицинская запись не найдена!");
        }
        log.info("Запись найдена. Исходные данные - {}", record);

        record.setDiagnosis(updateDto.getDiagnosis());
        record.setPrescription(updateDto.getPrescription());
        record.setNotes(updateDto.getNotes());
        record.setRecommendation(updateDto.getRecommendation());
        record.setLastUpdatedAt(LocalDateTime.now());
       // record.setLastUpdatedBy(authentication.getName());
        repo.save(record);
        log.info("КОНЕЦ: MedicalRecordServiceImpl - update(). Обновленная запись - {}", mapper.toDto(record));
        return mapper.toDto(record);
    }
    //@Transactional
    @Override
    public String delete(Long id) {
        log.info("СТАРТ: MedicalRecordServiceImpl - delete(). Мед запись с id {}", id);
        //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            log.error("Медицинская запись с id " + id + " не найдена!");
            throw new NullPointerException("Медицинская запись не найдена!");
        }

        record.setDeletedAt(LocalDateTime.now());
      //  record.setDeletedBy(authentication.getName());
        repo.save(record);
        log.info("КОНЕЦ: ServiceTypeServiceImpl - delete(). Мед запись (id {}) удалена", id);
        return "Медицинская запись удалена";
    }
}
