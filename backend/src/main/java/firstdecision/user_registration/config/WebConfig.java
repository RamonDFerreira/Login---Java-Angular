package firstdecision.user_registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Define as origens permitidas (ajuste conforme necessário)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:4200")); 
        
        // Define os métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Define os cabeçalhos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        
        // Permite o envio de credenciais (cookies, headers de autorização)
        configuration.setAllowCredentials(true);
        
        // Define o tempo máximo que o resultado de uma solicitação CORS pode ser armazenado em cache
        configuration.setMaxAge(3600L);
        
        // Aplica as configurações a todas as rotas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
    
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}