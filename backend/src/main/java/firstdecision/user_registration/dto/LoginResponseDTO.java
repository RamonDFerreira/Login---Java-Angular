package firstdecision.user_registration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String name;
    private String token;

    public LoginResponseDTO(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
