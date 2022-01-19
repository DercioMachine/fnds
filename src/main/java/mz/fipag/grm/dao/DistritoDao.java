package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Distrito;

public interface DistritoDao {

	void save(Distrito distrito);
	
	void update(Distrito distrito);
	
	void delete (Long id);
	
	Distrito findById(Long id);
	
	List<Distrito> findAll();

	List<Distrito> findAllById(Long id);
}
