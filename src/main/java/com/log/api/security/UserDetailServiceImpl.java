package com.log.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.log.domain.model.Administrador;
import com.log.domain.repository.AdministradorRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private AdministradorRepository admRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Administrador adm = admRepository.findByUsuario(username);
		
		if(adm == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new UserDetailsImpl(adm);
	}
}
