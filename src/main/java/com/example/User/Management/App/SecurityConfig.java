package com.example.User.Management.App;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Password encoding for security
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll()  // Allow anyone to access the registration endpoint
                        .requestMatchers("/api/users/all").hasRole("ADMIN")  // Only admins can access all users
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/api/users/all", true)  // Redirect to /api/users/all after successful login
                        .permitAll()  // Allow anyone to access the login page
                )
                .logout(logout -> logout
                        .permitAll()  // Allow logout to be accessed publicly
                )
                .httpBasic();  // Enable basic authentication for APIs

        return http.build();
    }
}









