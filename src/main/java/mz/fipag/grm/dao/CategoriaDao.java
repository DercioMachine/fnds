package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Categoria;

public interface CategoriaDao {

	void save(Categoria categoria);
	
	void update(Categoria categoria);
	
	void delete (Long id);
	
	Categoria findById(Long id);
	
	List<Categoria> findAll();
}
