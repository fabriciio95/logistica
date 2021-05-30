package com.log.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.log.domain.model.Administrador;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Administrador adm = mapper.readValue(request.getInputStream(), Administrador.class);
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(adm.getUsuario(), adm.getSenha()); 
			return authenticationManager.authenticate(upat);
		} catch(IOException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String jwtToken = Jwts.builder()
							.setSubject(userDetails.getUsername())
							.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TEMPO_EXPIRACAO))
							.signWith(SignatureAlgorithm.HS512, SecurityConstants.CHAVE_SECRETA)
							.compact();
		response.addHeader(SecurityConstants.CABECALHO_AUTORIZACAO, SecurityConstants.PREFIXO_TOKEN + jwtToken);
	}
}
