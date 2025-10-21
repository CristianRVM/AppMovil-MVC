package com.example.AppMovil.Seguridad;

import com.example.AppMovil.Servicio.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ConfigSeguridad {

    private final UserDetailsServiceImp userDetailsService;

    //Constructor
    public ConfigSeguridad(UserDetailsServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configura las reglas de autorización de peticiones HTTP
        http
                // Autenticación: usa mi UserDetailsService para cargar usuarios desde BD
                .userDetailsService(userDetailsService) // <-- Enlaza al userDetailsService
                //Desactiva CSRF solo para API REST
                .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/api/**")))
                // Autorización: define quién accede a qué rutas
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/**").permitAll()
                .requestMatchers("/login**", "/registrar", "/uploaded-images/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/imagenes/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/admin-dashboard", "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                )

                // Configuración propia de Login
                .formLogin(form -> form
                    .loginPage("/login") // Usará tu página personalizada en /login
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")      // <--- CLAVE
                    .passwordParameter("password")
                    .successHandler(successHandlerOK()) // Al login exitoso, ejecuta redirección
                    .permitAll() // Permite acceso sin autenticación a /login
                );

        //Construye el objeto SecurityFilterChain 
        return http.build();
    }

    public AuthenticationSuccessHandler successHandlerOK() {
        return (request, response, authentication) -> {
            response.sendRedirect("/");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
