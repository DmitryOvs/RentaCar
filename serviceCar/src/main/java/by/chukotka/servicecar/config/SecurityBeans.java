package by.chukotka.servicecar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.OAuth2ResourceServerDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/catalog-api/carsRent")
                        .hasAuthority("SCOPE_edit_catalog")
                        .requestMatchers(HttpMethod.PATCH, "/catalog-api/carsRent/{carId:\\d}")
                        .hasAuthority("SCOPE_edit_catalog")
                        .requestMatchers(HttpMethod.DELETE, "/catalog-api/carsRent/{carId:\\d}")
                        .hasAuthority("SCOPE_edit_catalog")
                        .requestMatchers(HttpMethod.GET)
                        .hasAuthority("SCOPE_view_catalog")
                        .anyRequest().denyAll())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oAuth2ResourceServer -> oAuth2ResourceServer.jwt(Customizer.withDefaults()))
                .build();
    }
}
