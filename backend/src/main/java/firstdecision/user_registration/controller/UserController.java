package firstdecision.user_registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import firstdecision.user_registration.dto.LoginDTO;
import firstdecision.user_registration.dto.LoginResponseDTO;
import firstdecision.user_registration.dto.UserDTO;
import firstdecision.user_registration.model.User;
import firstdecision.user_registration.service.UserService;

/* Configuração de CORS para permitir chamadas do front-end */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
    try {
      User user = userService.registerUser(userDTO);
      return ResponseEntity.ok(user);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
      try {
          LoginResponseDTO response = userService.authenticateUser(loginDTO);
          return ResponseEntity.ok(response);
      } catch (Exception e) {
          return ResponseEntity.status(401).body(e.getMessage());
      }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
      try {
          System.out.println("Buscando usuário com ID: " + id);
          User user = userService.getUserById(id);
          if (user != null) {
              System.out.println("Usuário encontrado: " + user.getName());
              return ResponseEntity.ok(user);
          } else {
              System.out.println("Usuário não encontrado.");
              return ResponseEntity.status(404).body("Usuário não encontrado");
          }
      } catch (Exception e) {
          System.out.println("Erro ao buscar usuário: " + e.getMessage());
          return ResponseEntity.status(500).body("Erro ao buscar usuário: " + e.getMessage());
      }
  }
}