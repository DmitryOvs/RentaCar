package by.chukotka.servicecar;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers("/catalog-api/**")
                        .hasRole("service")) //настройка прав доступа
                .httpBasic(Customizer.withDefaults()) //включение поддержки Basic аутентификации
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  //отключение поддержки Http сессий межсерверного взаимодействия
                .build();
    }
}
