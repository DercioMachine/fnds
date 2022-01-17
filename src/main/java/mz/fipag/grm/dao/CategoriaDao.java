package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.util.PaginacaoUtil;

public interface CategoriaDao {

	void save(Categoria categoria);
	
	void update(Categoria categoria);
	
	void delete (Long id);
	
	Categoria findById(Long id);
	
	List<Categoria> findAll();
	
	public PaginacaoUtil<Categoria> buscaPaginada(int pagina);
}
