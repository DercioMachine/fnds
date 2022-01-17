package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class ProjectoDaoImpl extends AbstractDao<Projecto, Long> implements ProjectoDao{
	
	@Override
	public PaginacaoUtil<Projecto> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<Projecto> projectos = getEntityManager()
				.createQuery("select p from Projecto p order by p.designacao asc", Projecto.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Projecto>(tamanho, pagina, totalDePaginas, projectos);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Projecto", Long.class)
				.getSingleResult();
	}

}
