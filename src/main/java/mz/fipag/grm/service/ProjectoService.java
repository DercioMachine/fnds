package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.util.PaginacaoUtil;

public interface ProjectoService {

	void salvar (Projecto projecto);
	
	void editar (Projecto projecto);
	
	void excluir (Long id);
	
	Projecto buscarPorId(Long id);
	
	List<Projecto> buscarTodos();
	
	public PaginacaoUtil<Projecto> buscaPorPagina(int pagina);
}
