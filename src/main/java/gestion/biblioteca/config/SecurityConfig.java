package gestion.biblioteca.config;

import gestion.biblioteca.security.JwtAuthFilter;
import gestion.biblioteca.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig
{

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        ///  Estas ruta no necesitan tener una autenticación, estan públicas
                        auth -> auth.requestMatchers(
                                        "/auth/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                ).permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/libros/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/libros/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/libros/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/libros/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/prestamos/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/prestamos/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/prestamos/**").hasRole("ADMIN")

                                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                                .requestMatchers("/api/roles/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement( s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}