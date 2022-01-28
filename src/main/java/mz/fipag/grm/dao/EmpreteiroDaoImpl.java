package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class EmpreteiroDaoImpl extends AbstractDao<Empreiteiro, Long> implements EmpreteiroDao{

	@Override
	public PaginacaoUtil<Empreiteiro> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<Empreiteiro> empreiteiros = getEntityManager()
				.createQuery("select e from Empreiteiro e order by e.nome asc", Empreiteiro.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Empreiteiro>(tamanho, pagina, totalDePaginas, empreiteiros);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Empreiteiro", Long.class)
				.getSingleResult();
	}

}
