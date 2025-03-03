package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="app_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<User> users;
}
