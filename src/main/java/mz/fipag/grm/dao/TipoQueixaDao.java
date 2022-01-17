package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.TipoQueixa;

public interface TipoQueixaDao {

void save(TipoQueixa tipoQueixa);
	
	void update(TipoQueixa tipoQueixa);
	
	void delete (Long id);
	
	TipoQueixa findById(Long id);
	
	List<TipoQueixa> findAll();
}
