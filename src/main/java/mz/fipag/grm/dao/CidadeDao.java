package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.util.PaginacaoUtil;

public interface CidadeDao {

	void save(Cidade cidade);
	
	void update(Cidade cidade);
	
	void delete (Long id);
	
	Cidade findById(Long id);
	
	List<Cidade> findAll();
	
	public PaginacaoUtil<Cidade> buscaPaginada(int pagina);
}
