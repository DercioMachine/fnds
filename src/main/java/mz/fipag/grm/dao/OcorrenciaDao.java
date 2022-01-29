package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

public interface OcorrenciaDao {

void save(Ocorrencia ocorrencia);
	
	void update(Ocorrencia ocorrencia);
	
	void delete (Long id);
	
	Ocorrencia findById(Long id);
	
	List<Ocorrencia> findAll();
	
	public PaginacaoUtil<Ocorrencia> buscaPaginada(int pagina);
}
