package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

public interface TipoOcorrenciaDao {

void save(TipoOcorrencia tipoOcorrencia);
	
	void update(TipoOcorrencia tipoOcorrencia);
	
	void delete (Long id);
	
	TipoOcorrencia findById(Long id);
	
	List<TipoOcorrencia> findAll();
	
	public PaginacaoUtil<TipoOcorrencia> buscaPaginada(int pagina);
}
