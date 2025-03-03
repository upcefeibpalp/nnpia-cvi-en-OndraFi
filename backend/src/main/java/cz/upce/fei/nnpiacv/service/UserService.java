package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.dto.UserRequestDto;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @PostConstruct
    public void init() {
    }

    public User findUser(long id) {
        log.debug("Find user with id {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User findUserByEmail(String email) {
        log.debug("Find user with email {}", email);
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public Collection<User> findUsers() {
        log.debug("Find all users");
        return userRepository.findAll();
    }


    public void deleteUser(long id) {
        log.debug("Delete user with id {}", id);
        userRepository.deleteById(id);
    }

    public User updateUser(long id, UserRequestDto userRequestDto) {
        User user = this.findUser(id);

        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        userRepository.save(user);

        return user;
    }
}
