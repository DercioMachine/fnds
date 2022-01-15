package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Provincia;

public interface ProvinciaService {

	void salvar (Provincia provincia);
	
	void editar (Provincia provincia);
	
	void excluir (Long id);
	
	Provincia buscarPorId(Long id);
	
	List<Provincia> buscarTodos();
}
