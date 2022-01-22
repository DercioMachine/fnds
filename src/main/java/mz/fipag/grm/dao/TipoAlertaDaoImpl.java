package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class TipoAlertaDaoImpl extends AbstractDao<TipoAlerta, Long> implements TipoAlertaDao{

	@Override
	public PaginacaoUtil<TipoAlerta> buscaPaginada(int pagina) {
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<TipoAlerta> tipoAlertas = getEntityManager()
				.createQuery("select ta from TipoAlerta ta order by ta.designacao asc", TipoAlerta.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<TipoAlerta>(tamanho, pagina, totalDePaginas, tipoAlertas);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from TipoAlerta", Long.class)
				.getSingleResult();
	}

}
