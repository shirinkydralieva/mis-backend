package itacademy.misbackend.mapper;

import itacademy.misbackend.dto.AddressDto;
import itacademy.misbackend.entity.helper.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
