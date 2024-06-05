package itacademy.misbackend.mapper.impl;

import itacademy.misbackend.dto.UserDto;
import itacademy.misbackend.entity.User;
import itacademy.misbackend.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        UserDto dto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .blocked(user.isBlocked())
                .build();
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles());
        } //Написать метод для доббавления роли пользователю
        return dto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .blocked(userDto.isBlocked())
                .build();
        if (userDto.getRoles() != null) {
            user.setRoles(userDto.getRoles());
        }
        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> user) {
        var dtos = new ArrayList<UserDto>();
        for (User u : user) {
            dtos.add(toDto(u));
        }
        return dtos;
    }
}
