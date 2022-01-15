package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Provincia;

public interface ProvinciaDao {

void save(Provincia provincia);
	
	void update(Provincia provincia);
	
	void delete (Long id);
	
	Provincia findById(Long id);
	
	List<Provincia> findAll();
}
