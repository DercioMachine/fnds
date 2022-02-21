package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Operacao;
import mz.fipag.grm.util.PaginacaoUtil;

public interface OperacaoService {

	void salvar (Operacao operacao);
	
	void editar (Operacao operacao);
	
	void excluir (Long id);
	
	Operacao buscarPorId(Long id);
	
	List<Operacao> buscarTodos();
	
	public PaginacaoUtil<Operacao> buscaPorPagina(int pagina);
}
