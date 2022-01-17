package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class PostoAdministrativoDaoImpl extends AbstractDao<PostoAdministrativo, Long> implements PostoAdministrativoDao{

	public PaginacaoUtil<PostoAdministrativo> buscaPaginada(int pagina){
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		
		List<PostoAdministrativo> postos = getEntityManager()
				.createQuery("select pa from PostoAdministrativo pa order by pa.designacao asc", PostoAdministrativo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<PostoAdministrativo>(tamanho, pagina, totalDePaginas, postos);
		
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from PostoAdministrativo", Long.class)
				.getSingleResult();
	}
}
