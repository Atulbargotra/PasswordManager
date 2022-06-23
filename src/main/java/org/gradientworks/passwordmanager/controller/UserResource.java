package org.gradientworks.passwordmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gradientworks.passwordmanager.domain.Authority;
import org.gradientworks.passwordmanager.domain.User;
import org.gradientworks.passwordmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserResource {
    private final UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("inside");
        URI saveUserURI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(saveUserURI).body(userService.saveUser(user));
    }

    @PostMapping("/authority/save")
    public ResponseEntity<?> addRole(@RequestBody Authority authority) {
        URI saveAuthorityURI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authority/save").toUriString());
        return ResponseEntity.created(saveAuthorityURI).body(userService.saveAuthority(authority));
    }

    @GetMapping("/users")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("Hello");
    }
}
