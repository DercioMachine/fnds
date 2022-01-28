package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.EmpreteiroDao;
import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class EmpreiteiroServiceImpl implements EmpreiterioService {

	
	@Autowired
	private EmpreteiroDao empreteiroDao;
	
	@Override
	public void salvar(Empreiteiro empreiteiro) {
		empreteiroDao.save(empreiteiro);
	}

	@Override
	public void editar(Empreiteiro empreiteiro) {
		empreteiroDao.update(empreiteiro);
	}

	@Override
	public void excluir(Long id) {
		empreteiroDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Empreiteiro buscarPorId(Long id) {
		return empreteiroDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Empreiteiro> buscarTodos() {
		return empreteiroDao.findAll();
	}

	@Override
	public PaginacaoUtil<Empreiteiro> buscaPorPagina(int pagina) {
		return empreteiroDao.buscaPaginada(pagina);
	}

}
