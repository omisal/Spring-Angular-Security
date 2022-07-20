package com.example.securityBack.restapi.api;

import com.example.securityBack.models.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/role")
@CrossOrigin(origins = "*")
public interface RoleAPI {

    @PostMapping
    ResponseEntity<?> newRole(@RequestBody Roles request);

    @PutMapping("/{id}")
    ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody Roles request);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable int id);

    @GetMapping
    ResponseEntity<?> getRoles();

    @GetMapping("/{id}")
    ResponseEntity<?> getRole(@PathVariable int id);
}