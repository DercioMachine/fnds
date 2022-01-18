package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class TipoOcorrenciaDaoImpl extends AbstractDao<TipoOcorrencia, Long> implements TipoOcorrenciaDao{

	@Override
	public PaginacaoUtil<TipoOcorrencia> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<TipoOcorrencia> tipoOcorrencias = getEntityManager()
				.createQuery("select to from TipoOcorrencia to order by to.designacao asc", TipoOcorrencia.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<TipoOcorrencia>(tamanho, pagina, totalDePaginas, tipoOcorrencias);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from TipoOcorrencia", Long.class)
				.getSingleResult();
	}
}
