package com.clinic.clinic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/api/register").permitAll() // Allow access to login and register pages without authentication
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Specify the custom login page URL
                                .defaultSuccessUrl("/", true) // Redirect to home after successful login
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") // Specify the logout URL
                                .logoutSuccessUrl("/login?logout") // Redirect to login page after successful logout
                                .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/register") // Disable CSRF for /api/register endpoint
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
