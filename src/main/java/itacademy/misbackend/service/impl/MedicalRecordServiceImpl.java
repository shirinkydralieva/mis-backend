package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.mapper.AppointmentMapper;
import itacademy.misbackend.repo.MedicalRecordRepo;
import itacademy.misbackend.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
      private MedicalRecordRepo repo;
      private AppointmentMapper mapper;
    @Override
    public MedicalRecordDto create(MedicalRecordDto recordDto) {
     /*   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
*/
        MedicalRecord record = MedicalRecord.builder()
                .summary(recordDto.getSummary())
                .notes(recordDto.getNotes())
              //  .appointments()
                .createdAt(new Timestamp(System.currentTimeMillis()))
             //   .createdBy(username)
                .lastUpdatedAt(new Timestamp(System.currentTimeMillis()))
             //   .lastUpdatedBy(username)
                .build();

        repo.save(record);

        recordDto.setCreatedAt(record.getCreatedAt());
        recordDto.setLastUpdatedAt(record.getLastUpdatedAt());

        return recordDto;
    }

    @Override
    public MedicalRecordDto getById(Long id) {
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            throw new NullPointerException("Медицинская запись с id " + id + " не найдена!");
        }
        return   MedicalRecordDto.builder()
                .id(record.getId())
                .summary(record.getSummary())
                .notes(record.getNotes())
                .appointments(mapper.toDtoList(record.getAppointments()))
                .createdAt(record.getCreatedAt())
                .createdBy(record.getCreatedBy())
                .lastUpdatedAt(record.getLastUpdatedAt())
                .lastUpdatedBy(record.getLastUpdatedBy())
                .build();
    }

    @Override
    public List<MedicalRecordDto> getAll() {
        List<MedicalRecord> recordList = repo.findAllByDeletedAtIsNull();
        if (recordList.isEmpty()) {
            throw new NullPointerException("Записей нет!");
        }
        List<MedicalRecordDto> recordDtoList = new ArrayList<>();
        for (MedicalRecord record : recordList) {
            MedicalRecordDto recordDto = MedicalRecordDto.builder()
                    .id(record.getId())
                    .summary(record.getSummary())
                    .notes(record.getNotes())
                    .appointments(mapper.toDtoList(record.getAppointments()))
                    .createdAt(record.getCreatedAt())
                    .createdBy(record.getCreatedBy())
                    .lastUpdatedAt(record.getLastUpdatedAt())
                    .lastUpdatedBy(record.getLastUpdatedBy())
                    .build();
            recordDtoList.add(recordDto);
        }
        return recordDtoList;
    }

    @Override
    public MedicalRecordDto update(Long id, MedicalRecordDto dto) {
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
            if (record == null) {
                throw new NullPointerException("Медицинская запись с id " + id + " не найдена!");
            }
        record.setNotes(dto.getNotes());
        record.setSummary(dto.getSummary());
        record.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
       // record.setLastUpdatedBy(username);

        repo.save(record);

        dto.setLastUpdatedAt(record.getLastUpdatedAt());
        dto.setLastUpdatedBy(record.getLastUpdatedBy());

        return dto;
    }


    @Override
    public String delete(Long id) {
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            throw new NullPointerException("Медицинская запись с id " + id + " не найдена!");
        }
        record.setDeletedAt(new Timestamp(System.currentTimeMillis()));
      //  record.setDeletedBy(username);

        return "Медицинская запись удалена";
    }
}
