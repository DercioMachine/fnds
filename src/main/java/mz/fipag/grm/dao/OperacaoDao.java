package mz.fipag.grm.dao;

import java.util.List;

import mz.fipag.grm.domain.Operacao;
import mz.fipag.grm.util.PaginacaoUtil;

public interface OperacaoDao {

void save(Operacao operacao);
	
	void update(Operacao operacao);
	
	void delete (Long id);
	
	Operacao findById(Long id);
	
	List<Operacao> findAll();
	
	public PaginacaoUtil<Operacao> buscaPaginada(int pagina);
}
