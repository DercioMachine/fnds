package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

public interface TipoOcorrenciaService {

	void salvar (TipoOcorrencia tipoOcorrencia);
	
	void editar (TipoOcorrencia tipoOcorrencia);
	
	void excluir (Long id);
	
	TipoOcorrencia buscarPorId(Long id);
	
	List<TipoOcorrencia> buscarTodos();
	
	public PaginacaoUtil<TipoOcorrencia> buscaPaginada(int pagina);
}
