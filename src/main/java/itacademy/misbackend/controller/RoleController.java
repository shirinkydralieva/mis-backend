package itacademy.misbackend.controller;

import itacademy.misbackend.entity.helper.Role;
import itacademy.misbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/roles/")
public class RoleController {

    private final RoleService service;


    @PostMapping("/create")
    public ResponseEntity<Long> save(@RequestBody Role role) {
        return service.save(role);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roles = service.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        Role role = service.findById(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Role role) {
        return service.update(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
