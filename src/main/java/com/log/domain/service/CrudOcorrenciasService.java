package com.log.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.domain.model.Entrega;
import com.log.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudOcorrenciasService {
	
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
	
	public Page<Ocorrencia> paginarListaOcorrencias(List<Ocorrencia> ocorrencia, Pageable pageable) {
		List<Ocorrencia> ocorrenciasOrdenada = ocorrencia.stream()
				.sorted((o1, o2) -> -o1.getDataRegistro().compareTo(o2.getDataRegistro())).collect(Collectors.toList());
		PagedListHolder<Ocorrencia> pagedListHolder = new PagedListHolder<>(ocorrenciasOrdenada);
		pagedListHolder.setPageSize(pageable.getPageSize());
		pagedListHolder.setPage(pageable.getPageNumber());
		return new PageImpl<>(pagedListHolder.getPageList(), pageable, ocorrenciasOrdenada.size());
	}
}
