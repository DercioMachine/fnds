package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

public interface OcorrenciaService {

	void salvar (Ocorrencia ocorrencia);
	
	void editar (Ocorrencia ocorrencia);
	
	void excluir (Long id);
	
	Ocorrencia buscarPorId(Long id);
	
	List<Ocorrencia> buscarTodos();
	
	public PaginacaoUtil<Ocorrencia> buscaPorPagina(int pagina);
}
