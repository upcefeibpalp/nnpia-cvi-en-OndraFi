package cz.upce.fei.nnpiacv.domain;
import cz.upce.fei.nnpiacv.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NonNull
    private String email;
    @NonNull
    private String password;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    @NonNull
    private Role role;

    public UserResponseDto toResponseDto() {
        return UserResponseDto.builder().id(id).email(email).password(password).build();
    }
}