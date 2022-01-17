package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.util.PaginacaoUtil;

public interface PostoAdministrativoDao {

void save(PostoAdministrativo postoAdministrativo);
	
	void update(PostoAdministrativo postoAdministrativo);
	
	void delete (Long id);
	
	PostoAdministrativo findById(Long id);
	
	List<PostoAdministrativo> findAll();
	
	public PaginacaoUtil<PostoAdministrativo> buscaPaginada(int pagina);
}
