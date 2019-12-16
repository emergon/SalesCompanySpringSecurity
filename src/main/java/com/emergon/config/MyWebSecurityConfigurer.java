/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergon.config;

import com.emergon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author anastasios
 */
//@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    //Users, password and roles

    @Autowired//This class is the datasource for custom User-Role
    private UserService userService;

    @Override//Configures users(in memory, database, etc)
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());//Used for custom security user-role
//auth.jdbcAuthentication().dataSource(securityDataSource);//this is for default security user-role
//        UserBuilder users = User.builder();
//        auth.inMemoryAuthentication()
//                .withUser(users.username("admin").password("{noop}1234").roles("ADMIN"))
//                .withUser(users.username("user").password("{noop}1234").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//Restrict access based on the HttpServletRequest
                //.anyRequest().authenticated()//Any request to the app must be authenticated(logged in)
                .antMatchers("/*/create", "/*/delete", "/*/update").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("USER", "ADMIN")// only USER can go to /
                .antMatchers("/admin/**").hasRole("ADMIN")// only ADMIN can go to /admin/subdirectories
                .and()
                .formLogin()//We are customizing the form login process
                .loginPage("/loginPage")//Show my form at the request mapping
                .loginProcessingUrl("/authenticate")//Login form will POST data to this URL for process username and password
                .permitAll()//Allow everyone to see login page. Don't have to be logged in.
                .and().logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");;
    }

    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
}
