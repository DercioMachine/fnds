package mz.fipag.grm.service;

import java.util.List;

import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.util.PaginacaoUtil;

public interface PostoAdministrativoService {

	void salvar (PostoAdministrativo postoAdministrativo);
	
	void editar (PostoAdministrativo postoAdministrativo);
	
	void excluir (Long id);
	
	PostoAdministrativo buscarPorId(Long id);
	
	List<PostoAdministrativo> buscarTodos();
	
	public PaginacaoUtil<PostoAdministrativo> buscaPorPagina(int pagina);
}
