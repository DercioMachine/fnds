package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Distrito;

public interface DistritoService {

	void salvar (Distrito distrito);
	
	void editar (Distrito distrito);
	
	void excluir (Long id);
	
	Distrito buscarPorId(Long id);
	
	List<Distrito> buscarTodos();
}
