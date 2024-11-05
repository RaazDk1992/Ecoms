package com.RaazDk.eComs.security;

import com.RaazDk.eComs.security.jwt.AuthEntryPoint;
import com.RaazDk.eComs.security.jwt.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    AuthEntryPoint unAuthorizedHandler;
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf-> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/api/auth/**"));
        http.authorizeHttpRequests((request)->request
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated());
        http.exceptionHandling((exception)->exception.authenticationEntryPoint(unAuthorizedHandler));
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());
        return http.build();
    }


}
