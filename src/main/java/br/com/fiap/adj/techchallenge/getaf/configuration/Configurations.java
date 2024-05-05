package br.com.fiap.adj.techchallenge.getaf.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class Configurations {

    @Autowired
    private FilterToken filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .anyRequest().permitAll()
//                .requestMatchers(HttpMethod.POST, "/login")
//                .permitAll()
//                .requestMatchers(HttpMethod.POST, "/usuarios")
//                .permitAll()
//                .requestMatchers(HttpMethod.GET, "/usuarios")
//                .permitAll()
//                .requestMatchers(HttpMethod.GET, "/usuarios/")
//                .permitAll()
//                .requestMatchers(HttpMethod.PUT, "/usuarios/")
//                .permitAll()
//                .requestMatchers(HttpMethod.DELETE, "/usuarios/")
//                .permitAll()
//                .anyRequest().authenticated()
                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder gerarCriptografia() {
        BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();
        return criptografia;
    }

}
