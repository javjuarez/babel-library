package com.example.babellibrary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = passwordEncoder(); 
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);
        
        builder.inMemoryAuthentication()
        .withUser(users.username("admin").password("admin").roles("ADMIN", "BIBLIO"))
        .withUser(users.username("biblio").password("biblio").roles("BIBLIO"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/index", "/buscar", "/ver/**", "/upload/**", "/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/uploads/**").hasAnyRole("ADMIN")
                .antMatchers("/registrar/**").hasAnyRole("ADMIN")
                .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
                .antMatchers("/editar/**").hasAnyRole("BIBLIO")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/error_403")
            ;
    }
    
}