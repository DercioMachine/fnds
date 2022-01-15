package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.CategoriaDao;
import mz.fipag.grm.domain.Categoria;

@Service
@Transactional(readOnly = false)
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	public void salvar(Categoria categoria) {
		categoriaDao.save(categoria);
	}

	@Override
	public void editar(Categoria categoria) {
		categoriaDao.update(categoria);
	}

	@Override
	public void excluir(Long id) {
		categoriaDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Categoria buscarPorId(Long id) {
		return categoriaDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Categoria> buscarTodos() {
		return categoriaDao.findAll();
	}

}
