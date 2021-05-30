package com.log.domain.test;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.log.domain.model.Administrador;
import com.log.domain.repository.AdministradorRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Component
public class InserirDadosIniciais {

	private AdministradorRepository admRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Administrador admExistente = admRepository.findByUsuario("admin");
		if(admExistente == null) {
			Administrador adm = new Administrador();
			adm.setUsuario("admin");
			adm.setSenha("admin");
			adm.criptografarSenha();
			admRepository.save(adm);
		}
	}
}
