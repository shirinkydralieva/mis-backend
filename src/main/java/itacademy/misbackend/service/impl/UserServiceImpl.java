package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.UserDto;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.entity.User;
import itacademy.misbackend.mapper.UserMapper;
import itacademy.misbackend.repo.UserRepo;
import itacademy.misbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;


    @Override
    public UserDto create(UserDto userDto) {
        log.info("СТАРТ: UserServiceImpl - create() {}", userDto);
        //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        user = userRepo.save(user);
        log.info("КОНЕЦ: UserServiceImpl - create {} ", userMapper.toDto(user));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        log.info("СТАРТ: UserServiceImpl - getById({})", id);
        User user = userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (user == null) {
            log.error("Пользователь с id " + id + " не найден!");
            throw new NullPointerException("Пользователь не найден!");
        }
        log.info("КОНЕЦ: UserServiceImpl - getById(). Пользователь - {} ", userMapper.toDto(user));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.info("СТАРТ: UserServiceImpl - getAll()");
        var userList = userRepo.findAllByDeletedAtIsNullAndDeletedByIsNull();
        if (userList.isEmpty()) {
            log.error("Пользователей нет!");
            throw new NullPointerException("Пользователей нет!");
        }
        log.info("КОНЕЦ: MedicalRecordServiceImpl - getAll()");
        return userMapper.toDtoList(userList);
    }

    /*@Override
    public UserDto update(UserDto userDto) {
        User user = userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(userDto.getId());
    }*/

    /*@Override
    public String delete(Long id) {
        return null;
    }*/
}
