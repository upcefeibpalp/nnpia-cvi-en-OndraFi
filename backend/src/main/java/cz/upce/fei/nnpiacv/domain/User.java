package cz.upce.fei.nnpiacv.domain;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="app_user")
public class User {
    @Id
    private long id;
    @Column(unique=true)
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}