package cz.upce.fei.nnpiacv.component;

import cz.upce.fei.nnpiacv.domain.Role;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.repository.RoleRepository;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role(0L,"USER_ROLE");
        if(!roleRepository.existsById(role.getId())) {
            roleRepository.save(role);
        }
        User user = new User(0L, "admin@upce.cz", "adminadmin", role);
        if (!userRepository.existsById(user.getId())) {
            log.debug("Admin user saved{}", user);
            this.userRepository.save(user);
        }
    }
}