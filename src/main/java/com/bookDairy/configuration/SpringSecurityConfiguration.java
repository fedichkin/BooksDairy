package com.bookDairy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.bookDairy.service"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password")
//                .authorities("ROLE_USER");
//    }

@Bean
public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*.css", "/*.jpg","/error").permitAll()
                .antMatchers("/api/", "/api/books").permitAll()
                .antMatchers("/api/books/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/api")
                .invalidateHttpSession(true).permitAll()
                .and()
                .csrf().disable()
                ;
    }
}
