package org.sample.moviedb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(config -> config.disable())
			.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.NEVER))
			.headers(config -> config.frameOptions().disable())
			.logout(config -> config.logoutSuccessUrl("/"))
			.oauth2Login(config -> config.defaultSuccessUrl("/"))
			.httpBasic(config -> config.disable())
			.formLogin(config -> config.disable())
			.authorizeHttpRequests(request -> request
				.antMatchers("/api/ad/**/*").hasRole("ADMIN")
				.antMatchers("/api/us/**/*").hasAnyRole("ADMIN", "USER")
				.anyRequest().permitAll());
		return http.build();
	}
	
}
