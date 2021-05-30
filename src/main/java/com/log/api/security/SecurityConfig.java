package com.log.api.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.httpBasic()
			.and()
				.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
	}
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST", "GET", "PUT", "DELETE")
				.exposedHeaders(SecurityConstants.CABECALHO_AUTORIZACAO);
	}
}
