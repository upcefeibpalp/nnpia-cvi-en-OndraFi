package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    /*
        @GetMapping("/user")
        public User
        findUser(@RequestParam long id) {
            return userService.findUser(id);
        }
      */


    @GetMapping("/user/{id}")
    public User
    findUser(@PathVariable long id) {
        return userService.findUser(id);
    }

    @GetMapping("/users")
    public Collection<User> findUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            if (user == null) {
                return Collections.emptyList();
            }else {
                return Collections.singletonList(user);
            }
        }
        return userService.findUsers();
    }

}