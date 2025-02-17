package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final Map<Long,User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        User user1 = new User(0L,"fialka.ondra@gmail.com","heslo123");
        User user2 = new User(1L,"testt@gmail.com","heslo123");
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
    }

    public User findUser(long id) {
        logger.info("Find user with id {}", id);
        return users.get(id);
    }

    public Collection<User> findUsers() {
        return users.values();
    }


}
