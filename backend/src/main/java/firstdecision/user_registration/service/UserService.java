package firstdecision.user_registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import firstdecision.user_registration.dto.LoginDTO;
import firstdecision.user_registration.dto.LoginResponseDTO;
import firstdecision.user_registration.dto.UserDTO;
import firstdecision.user_registration.model.User;
import firstdecision.user_registration.repository.UserRepository;
import firstdecision.user_registration.utils.JwtTokenUtil;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public User registerUser(UserDTO userDTO) throws Exception {
    validateUserData(userDTO);

    if (userRepository.findByEmail(userDTO.getEmail()) != null) {
        throw new Exception("E-mail já cadastrado.");
    }

    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    
    // Criptografa a senha antes de salvar
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  private void validateUserData(UserDTO userDTO) throws Exception {
    if (!StringUtils.hasLength(userDTO.getName()) || userDTO.getName().length() < 3 || userDTO.getName().length() > 50) {
      throw new Exception("O nome deve ter entre 3 e 50 caracteres.");
    }

    if (!StringUtils.hasLength(userDTO.getEmail()) || !userDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
      throw new Exception("E-mail inválido.");
    }

    if (!StringUtils.hasLength(userDTO.getPassword()) || userDTO.getPassword().length() < 6 || userDTO.getPassword().length() > 20) {
      throw new Exception("A senha deve ter entre 6 e 20 caracteres.");
    }
  }

  public LoginResponseDTO authenticateUser(LoginDTO loginDTO) throws Exception {
    User user = userRepository.findByEmail(loginDTO.getEmail());

    if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
        String token = jwtTokenUtil.generateToken(user);
        return new LoginResponseDTO(user.getName(), token);
    } else {
        throw new Exception("Credenciais inválidas");
    }
  }
}