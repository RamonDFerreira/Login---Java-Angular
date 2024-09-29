package firstdecision.user_registration;

import firstdecision.user_registration.dto.UserDTO;
import firstdecision.user_registration.model.User;
import firstdecision.user_registration.repository.UserRepository;
import firstdecision.user_registration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder; // Use @Mock ao invés de @Autowired

  @InjectMocks
  private UserService userService;

  @Test
  void testRegisterUser_Success() throws Exception {
    // Arrange
    UserDTO userDTO = new UserDTO();
    userDTO.setName("Teste User");
    userDTO.setEmail("teste@example.com");
    userDTO.setPassword("password123");

    // Configurar o comportamento do mock para passwordEncoder.encode()
    when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

    // Configurar o comportamento do mock para userRepository.findByEmail()
    when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);

    // Configurar o comportamento do mock para userRepository.save()
    User savedUser = new User();
    savedUser.setId(1L);
    savedUser.setName(userDTO.getName());
    savedUser.setEmail(userDTO.getEmail());
    savedUser.setPassword("encodedPassword123");

    when(userRepository.save(any(User.class))).thenReturn(savedUser);

    // Act
    User result = userService.registerUser(userDTO);

    // Assert
    assertNotNull(result);
    assertEquals(savedUser.getId(), result.getId());
    assertEquals(savedUser.getEmail(), result.getEmail());
    assertEquals("encodedPassword123", result.getPassword());
    verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
    verify(userRepository, times(1)).save(any(User.class));
  }
}
