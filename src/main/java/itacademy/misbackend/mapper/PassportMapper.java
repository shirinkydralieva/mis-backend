package itacademy.misbackend.mapper;

import itacademy.misbackend.dto.PassportDto;
import itacademy.misbackend.entity.helper.Passport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassportMapper {
     PassportDto toDto(Passport passport);
     Passport toEntity(PassportDto passportDto);
     List<PassportDto> toDtoList(List<Passport> passport);
}
