package itacademy.misbackend.service;

import itacademy.misbackend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    //UserDto update(UserDto userDto);
    //void block(Long userId);
    //void unblock(Long userId);
    //String delete(Long id); Допишу после подключения security
}
