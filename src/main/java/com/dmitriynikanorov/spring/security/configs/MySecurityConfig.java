package com.dmitriynikanorov.spring.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);

        //        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("dmitriy")
//                        .password("dmitriy")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder.username("second")
//                        .password("second")
//                        .roles("HR"))
//                .withUser(userBuilder.username("boss")
//                        .password("boss")
//                        .roles("HR", "MANAGER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info/**").hasRole("MANAGER")
                .and().formLogin().permitAll();
    }
}
