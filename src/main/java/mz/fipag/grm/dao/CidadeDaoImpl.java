package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.util.PaginacaoUtil;

@Repository
public class CidadeDaoImpl extends AbstractDao<Cidade, Long> implements CidadeDao{

	@Override
	public PaginacaoUtil<Cidade> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina-1) * tamanho;
		
		List<Cidade> cidades = getEntityManager()
				.createQuery("select c from Cidade c order by c.designacao asc", Cidade.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistos = count();
		long totalDePaginas = (totalRegistos +(tamanho-1))/ tamanho;
		
		return new PaginacaoUtil<Cidade>(tamanho, pagina, totalDePaginas, cidades);
	}
	
	public long count() {
		
		return getEntityManager()
				.createQuery("select count(*) from Cidade", Long.class)
				.getSingleResult();
	}

}
