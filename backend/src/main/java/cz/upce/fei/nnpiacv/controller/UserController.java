package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.Role;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.dto.UserRequestDto;
import cz.upce.fei.nnpiacv.dto.UserResponseDto;
import cz.upce.fei.nnpiacv.repository.RoleRepository;
import cz.upce.fei.nnpiacv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;


@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    public UserResponseDto
    findUser(@PathVariable long id) {
        return userService.findUser(id).toResponseDto();
    }

    @GetMapping
    public Collection<User> findUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            if (user == null) {
                return Collections.emptyList();
            } else {
                return Collections.singletonList(user);
            }
        }
        return userService.findUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto user) {
        log.info("Request for creating user obtained {}", user);

        Role role = roleRepository.findByName("USER_ROLE");

        User createdUser = userService.createUser(user.toUser(role));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser.toResponseDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id,@RequestBody UserRequestDto userRequestDto) {
        log.info("Request for updating user obtained {}", userRequestDto);
        User user = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(user.toResponseDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        log.info("Request for deleting user obtained");
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}