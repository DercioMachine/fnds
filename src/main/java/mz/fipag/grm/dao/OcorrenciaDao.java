package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Ocorrencia;

public interface OcorrenciaDao {

void save(Ocorrencia ocorrencia);
	
	void update(Ocorrencia ocorrencia);
	
	void delete (Long id);
	
	Ocorrencia findById(Long id);
	
	List<Ocorrencia> findAll();
}
