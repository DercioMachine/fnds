package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.util.PaginacaoUtil;

public interface EmpreteiroDao {

	void save(Empreiteiro empreiteiro);
	
	void update(Empreiteiro empreiteiro);
	
	void delete (Long id);
	
	Empreiteiro findById(Long id);
	
	List<Empreiteiro> findAll();
	
	public PaginacaoUtil<Empreiteiro> buscaPaginada(int pagina);
}
