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
//                .authorizeRequests()
//                .antMatchers("/Accommodations").authenticated()
//                .and().httpBasic().authenticationEntryPoint((request, response, authException) -> {
//            String requestedBy = request.getHeader("X-Requested-By");
////                            log.info("X-Requested-By: " + requestedBy);
//            if (requestedBy == null || requestedBy.isEmpty()) {
//                HttpServletResponse httpResponse = (HttpServletResponse) response;
//                httpResponse.addHeader("WWW-Authenticate", "Basic realm=Cascade Realm");
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//            } else {
//                HttpServletResponse httpResponse = (HttpServletResponse) response;
//                httpResponse.addHeader("WWW-Authenticate", "Application driven");
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//            }
//        });

    }
}
