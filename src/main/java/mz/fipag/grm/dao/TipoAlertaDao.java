package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.util.PaginacaoUtil;

public interface TipoAlertaDao {

	void save(TipoAlerta tipoAlerta);
	
	void update(TipoAlerta tipoAlerta);
	
	void delete (Long id);
	
	TipoAlerta findById(Long id);
	
	List<TipoAlerta> findAll();
	
	public PaginacaoUtil<TipoAlerta> buscaPaginada(int pagina);
}
