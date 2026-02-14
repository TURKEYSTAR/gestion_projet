package com.projet.javaee.config;

import com.projet.javaee.config.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth
                            // Attention : hasRole("ADMINISTRATEUR") cherchera "ROLE_ADMINISTRATEUR"
                            .requestMatchers("/dashboard").hasAnyRole("ADMINISTRATEUR", "GESTIONNAIRE")
                            .requestMatchers("/projets/import").hasAnyRole("ADMINISTRATEUR", "GESTIONNAIRE")
                            .requestMatchers("/projets/**").authenticated()
                            .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2
                            .loginPage("/login")
                            .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                            .successHandler(customAuthenticationSuccessHandler);
                })
                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll());

        return http.build();
    }
}
