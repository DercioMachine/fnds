package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class OcorrenciaDaoImpl extends AbstractDao<Ocorrencia, Long> implements OcorrenciaDao{

	@Override
	public PaginacaoUtil<Ocorrencia> buscaPaginada(int pagina) {
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<Ocorrencia> ocorrencias = getEntityManager()
				
				.createQuery("select o from Ocorrencia o order by o.created desc", Ocorrencia.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Ocorrencia>(tamanho, pagina, totalDePaginas, ocorrencias);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Ocorrencia", Long.class)
				.getSingleResult();
	}

}
