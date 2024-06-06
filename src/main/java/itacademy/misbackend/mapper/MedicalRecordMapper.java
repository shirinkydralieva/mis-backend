package itacademy.misbackend.mapper;

import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.entity.MedicalRecord;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    MedicalRecordDto toDto(MedicalRecord record);

    MedicalRecord toEntity(MedicalRecordDto recordDto);

    List<MedicalRecordDto> toDtoList(List<MedicalRecord> recordList);
}

