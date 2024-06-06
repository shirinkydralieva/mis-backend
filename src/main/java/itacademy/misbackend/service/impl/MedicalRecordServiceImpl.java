package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.mapper.MedicalRecordMapper;
import itacademy.misbackend.repo.MedicalRecordRepo;
import itacademy.misbackend.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
      private MedicalRecordRepo repo;
      private MedicalRecordMapper mapper;
    @Override
    public MedicalRecordDto create(MedicalRecordDto recordDto) {
     //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     //   String username = authentication.getName();

        MedicalRecord record = mapper.toEntity(recordDto);
        record.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    //    record.setCreatedBy(username);
        record.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
     //   record.setLastUpdatedBy(username);

        repo.save(record);
        return mapper.toDto(record);
    }

    @Override
    public MedicalRecordDto getById(Long id) {
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
        if (record == null) {
            throw new NullPointerException("Медицинская запись с id " + id + " не найдена!");
        }
        return  mapper.toDto(record);
    }

    @Override
    public List<MedicalRecordDto> getAll() {
        List<MedicalRecord> recordList = repo.findAllByDeletedAtIsNull();
        if (recordList.isEmpty()) {
            throw new NullPointerException("Записей нет!");
        }
        return mapper.toDtoList(recordList);
    }

    @Override //переписать???
    public MedicalRecordDto update(Long id, MedicalRecordDto dto) {
        MedicalRecord record = repo.findByDeletedAtIsNullAndId(id);
            if (record == null) {
                throw new NullPointerException("Медицинская запись с id " + id + " не найдена!");
            }
        record.setNotes(dto.getNotes());
        record.setRecommendation(dto.getRecommendation());
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
