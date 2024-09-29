package firstdecision.user_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import firstdecision.user_registration.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}