package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.util.PaginacaoUtil;

public interface EmpreiterioService {

	void salvar (Empreiteiro empreiteiro);
	
	void editar (Empreiteiro empreiteiro);
	
	void excluir (Long id);
	
	Empreiteiro buscarPorId(Long id);
	
	List<Empreiteiro> buscarTodos();
	
	public PaginacaoUtil<Empreiteiro> buscaPorPagina(int pagina);
}
