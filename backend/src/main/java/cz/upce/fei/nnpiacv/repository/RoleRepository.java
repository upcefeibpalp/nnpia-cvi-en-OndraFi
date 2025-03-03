package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(@NonNull String name);

    boolean existsByName(@NonNull String name);
}
