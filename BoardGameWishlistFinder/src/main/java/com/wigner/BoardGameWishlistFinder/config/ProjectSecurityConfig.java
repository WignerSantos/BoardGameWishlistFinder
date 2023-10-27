package com.wigner.BoardGameWishlistFinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/admin/**"))
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/home").authenticated()
                        .requestMatchers("/boardgames").authenticated()
                        .requestMatchers("/boardgame/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN"))
                        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                                .defaultSuccessUrl("/home").failureUrl("/login?error=true"))
                        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                                .invalidateHttpSession(true).permitAll())
                        .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
