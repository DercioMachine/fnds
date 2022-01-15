package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.PostoAdministrativo;

public interface PostoAdministrativoDao {

void save(PostoAdministrativo postoAdministrativo);
	
	void update(PostoAdministrativo postoAdministrativo);
	
	void delete (Long id);
	
	PostoAdministrativo findById(Long id);
	
	List<PostoAdministrativo> findAll();
}
