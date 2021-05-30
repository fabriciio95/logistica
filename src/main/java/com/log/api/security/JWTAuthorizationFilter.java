package com.log.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader(SecurityConstants.CABECALHO_AUTORIZACAO);
		
		if(token != null && token.startsWith(SecurityConstants.PREFIXO_TOKEN)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		String usuario = Jwts.parser().setSigningKey(SecurityConstants.CHAVE_SECRETA)
							 .parseClaimsJws(token.replace(SecurityConstants.PREFIXO_TOKEN, ""))
							 .getBody().getSubject();
		if(usuario != null) {
			return new UsernamePasswordAuthenticationToken(usuario, null, AuthorityUtils.NO_AUTHORITIES);
		}
		
		return null;
	}
}
