// JwtTokenUtil.java
package firstdecision.user_registration.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import firstdecision.user_registration.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey; 

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey); 

        return JWT.create()
                .withSubject(user.getEmail()) 
                .withIssuedAt(new Date()) 
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // Token válido por 1 dia (em milissegundos)
                .withClaim("name", user.getName()) 
                .sign(algorithm); 
    }
}
