package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Categoria;

public interface CategoriaService {

	void salvar (Categoria categoria);
	
	void editar (Categoria categoria);
	
	void excluir (Long id);
	
	Categoria buscarPorId(Long id);
	
	List<Categoria> buscarTodos();
}
