package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.util.PaginacaoUtil;

public interface ProjectoDao {

void save(Projecto projecto);
	
	void update(Projecto projecto);
	
	void delete (Long id);
	
	Projecto findById(Long id);
	
	List<Projecto> findAll();
	
	public PaginacaoUtil<Projecto> buscaPaginada(int pagina);
}
