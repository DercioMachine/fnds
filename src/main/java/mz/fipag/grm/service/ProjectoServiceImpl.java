package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.ProjectoDao;
import mz.fipag.grm.domain.Projecto;

@Service
@Transactional(readOnly = false)
public class ProjectoServiceImpl implements ProjectoService{
	
	@Autowired
	ProjectoDao projectoDao;

	@Override
	public void salvar(Projecto projecto) {
		projectoDao.save(projecto);
	}

	@Override
	public void editar(Projecto projecto) {
		projectoDao.update(projecto);
	}

	@Override
	public void excluir(Long id) {
		projectoDao.delete(id);
	}

	@Override @Transactional(readOnly = false)
	public Projecto buscarPorId(Long id) {
		return projectoDao.findById(id);
	}

	@Override @Transactional(readOnly = false)
	public List<Projecto> buscarTodos() {
		return projectoDao.findAll();
	}

}
