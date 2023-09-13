package com.wigner.BoardGameWishlistFinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers(""))
                .authorizeHttpRequests((request) -> request.requestMatchers("/login").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/dashboard").permitAll())
                        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true"))
                        .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
