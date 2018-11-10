package com.spiderman.landlordcommunicationapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends
        WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
//        http
//      .headers().disable();

        http.authorizeRequests().antMatchers("/").permitAll();
        http.csrf().disable();
    }
}
