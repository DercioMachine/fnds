package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.util.PaginacaoUtil;

public interface TipoAlertaService {

	void salvar (TipoAlerta tipoAlerta);
	
	void editar (TipoAlerta tipoAlerta);
	
	void excluir (Long id);
	
	TipoAlerta buscarPorId(Long id);
	
	List<TipoAlerta> buscarTodos();
	
	public PaginacaoUtil<TipoAlerta> buscaPorPagina(int pagina);
}
