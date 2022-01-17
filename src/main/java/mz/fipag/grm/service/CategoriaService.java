package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.util.PaginacaoUtil;

public interface CategoriaService {

	void salvar (Categoria categoria);
	
	void editar (Categoria categoria);
	
	void excluir (Long id);
	
	Categoria buscarPorId(Long id);
	
	List<Categoria> buscarTodos();
	
	public PaginacaoUtil<Categoria> buscaPorPagina(int pagina);
}
