package cz.upce.fei.nnpiacv.component;

import cz.upce.fei.nnpiacv.domain.Role;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.repository.RoleRepository;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
        Role role = new Role("ADMIN_ROLE");
        if(!roleRepository.existsByName(role.getName())) {
            roleRepository.save(role);
        }
        Role userRole = new Role("USER_ROLE");
        if(!roleRepository.existsByName(userRole.getName())) {
            roleRepository.save(userRole);
        }
        User user = new User("admin@upce.cz", "adminadmin",role);
        if (!userRepository.existsByEmail(user.getEmail())) {
            log.debug("Admin user saved{}", user);
            this.userRepository.save(user);
        }
    }
}