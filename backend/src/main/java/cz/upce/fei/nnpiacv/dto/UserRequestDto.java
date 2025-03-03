package cz.upce.fei.nnpiacv.dto;

import cz.upce.fei.nnpiacv.domain.Role;
import cz.upce.fei.nnpiacv.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;

    public User toUser(Role role){
        return new User(email, password,role);
    }
}
