package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Operacao;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class OperacaoDaoImpl extends AbstractDao<Operacao, Long> implements OperacaoDao{

	@Override
	public PaginacaoUtil<Operacao> buscaPaginada(int pagina) {
		int tamanho = 10;
		int inicio = (pagina-1) * tamanho;
		
		List<Operacao> operacoes = getEntityManager()
				
				.createQuery("select o from Operacao o order by o.id desc", Operacao.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Operacao>(tamanho, pagina, totalDePaginas, operacoes);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Operacao", Long.class)
				.getSingleResult();
	}

}
