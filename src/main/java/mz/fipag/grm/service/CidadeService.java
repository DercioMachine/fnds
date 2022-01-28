package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.util.PaginacaoUtil;

public interface CidadeService {

	void salvar (Cidade cidade);
	
	void editar (Cidade cidade);
	
	void excluir (Long id);
	
	Cidade buscarPorId(Long id);
	
	List<Cidade> buscarTodos();
	
	public PaginacaoUtil<Cidade> buscaPorPagina(int pagina);
}
