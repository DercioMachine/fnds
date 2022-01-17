package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.TipoQueixa;

public interface TipoQueixaService {

	void salvar (TipoQueixa tipoQueixa);
	
	void editar (TipoQueixa tipoQueixa);
	
	void excluir (Long id);
	
	TipoQueixa buscarPorId(Long id);
	
	List<TipoQueixa> buscarTodos();
}
