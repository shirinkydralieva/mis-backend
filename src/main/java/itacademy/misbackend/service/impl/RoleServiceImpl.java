package itacademy.misbackend.service.impl;

import itacademy.misbackend.entity.Role;
import itacademy.misbackend.repo.RoleRepo;
import itacademy.misbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    @Override
    public ResponseEntity<Long> save(Role role) {
        Role savedRole = roleRepo.save(role);
        return new ResponseEntity<>(savedRole.getId(), HttpStatus.CREATED);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        return optionalRole.orElse(null);
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public ResponseEntity<String> update(Long id, Role role) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isPresent()) {
            role.setId(id);
            roleRepo.save(role);
            return new ResponseEntity<>("Роль успешно обновлена", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Роль не найдена", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isPresent()) {
            roleRepo.deleteById(id);
            return new ResponseEntity<>("Роль успешно удалена", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Роль не найдена", HttpStatus.NOT_FOUND);
        }
    }
}
