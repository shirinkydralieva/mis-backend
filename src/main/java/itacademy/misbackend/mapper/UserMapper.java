package itacademy.misbackend.mapper;

import itacademy.misbackend.dto.UserDto;
import itacademy.misbackend.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
public interface UserMapper {
     UserDto toDto(User user);
     User toEntity(UserDto userDto);
     List<UserDto> toDtoList(List<User> user);
}
