package firstdecision.user_registration.dto;

import lombok.Data;

@Data
public class UserDTO {
  private String name;
  private String email;
  private String password;
}