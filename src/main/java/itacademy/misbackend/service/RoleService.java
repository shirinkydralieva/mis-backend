package itacademy.misbackend.service;

import itacademy.misbackend.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity<Long> save(Role role);
    List<Role> findAll();
    Role findById(Long id);
    Role findByName(String name);
    ResponseEntity<String> update(Long id, Role role);
    ResponseEntity<String> delete(Long id);
}
