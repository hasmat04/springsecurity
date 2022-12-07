package com.jpasecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jpasecurity.service.JpaUserDetailsService;

@Configuration("com.jpasecurity.service")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	private final JpaUserDetailsService myUserDetailsService;

	
	public SecurityConfig(JpaUserDetailsService myUserDetailsService) {
		super();
		this.myUserDetailsService = myUserDetailsService;
	}



	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
				.authorizeRequests(auth ->  auth
						.antMatchers("/h2-console/**").permitAll()
						.mvcMatchers("/api/posts").permitAll()
						.anyRequest().authenticated())
				.userDetailsService(myUserDetailsService)
				.headers(headers ->headers.frameOptions().sameOrigin())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
