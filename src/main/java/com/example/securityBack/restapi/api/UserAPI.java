package com.example.securityBack.restapi.api;

import com.example.securityBack.models.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public interface UserAPI {

    @PostMapping
    ResponseEntity<?> newUser(@RequestBody Users request);

    @GetMapping
    ResponseEntity<?> getUsers();

    @GetMapping("/{id}")
    ResponseEntity<?> getUser(@PathVariable long id);
}