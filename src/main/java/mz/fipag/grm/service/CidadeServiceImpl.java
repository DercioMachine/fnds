package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.CidadeDao;
import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class CidadeServiceImpl implements CidadeService{
	
	@Autowired
	private CidadeDao cidadeDao;

	@Override
	public void salvar(Cidade cidade) {
		cidadeDao.save(cidade);
	}

	@Override
	public void editar(Cidade cidade) {
		cidadeDao.update(cidade);
	}

	@Override
	public void excluir(Long id) {
		cidadeDao.delete(id);
	}

	@Override
	public Cidade buscarPorId(Long id) {
		return cidadeDao.findById(id);
	}

	@Override @Transactional(readOnly = false)
	public List<Cidade> buscarTodos() {
		return cidadeDao.findAll();
	}

	@Override @Transactional(readOnly = false)
	public PaginacaoUtil<Cidade> buscaPorPagina(int pagina) {
		return cidadeDao.buscaPaginada(pagina);
	}

}
