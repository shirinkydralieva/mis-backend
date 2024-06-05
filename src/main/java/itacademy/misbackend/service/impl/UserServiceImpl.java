package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.UserDto;
import itacademy.misbackend.entity.User;
import itacademy.misbackend.mapper.UserMapper;
import itacademy.misbackend.repo.UserRepo;
import itacademy.misbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;


    @Override
    public UserDto create(UserDto userDto) {
        //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        user = userRepo.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepo.findAllByDeletedAtIsNullAndDeletedByIsNull());
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
