package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class CategoriaDaoImpl extends AbstractDao<Categoria, Long> implements CategoriaDao{

	@Override
	public PaginacaoUtil<Categoria> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<Categoria> categorias = getEntityManager()
				.createQuery("select c from Categoria c order by c.designacao asc", Categoria.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Categoria>(tamanho, pagina, totalDePaginas, categorias);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Categoria", Long.class)
				.getSingleResult();
	}

}
