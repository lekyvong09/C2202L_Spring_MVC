package com.ray.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("admin").password("password").roles("MANAGERS"));
		auth.inMemoryAuthentication().withUser(users.username("user").password("password").roles("EMPLOYEE"));
	}

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/login")
				.permitAll();
	}

}
