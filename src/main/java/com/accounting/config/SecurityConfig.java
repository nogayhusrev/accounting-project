package com.accounting.config;

import com.accounting.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final AuthSuccessHandler authSuccessHandler;
    private final SecurityService securityService;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler, SecurityService securityService) {
        this.authSuccessHandler = authSuccessHandler;
        this.securityService = securityService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .authorizeRequests()
                .antMatchers("/users/**").hasAnyAuthority("Root User", "Admin")
                .antMatchers("/companies/**").hasAnyAuthority("Root User")
                .antMatchers("/", "/login", "fragments", "/assets/**", "/img/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .tokenValiditySeconds(86400)
                .key("ngy")
                .userDetailsService(securityService)
                .and().build();
    }
}
