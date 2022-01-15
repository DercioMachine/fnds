package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.DistritoDao;
import mz.fipag.grm.domain.Distrito;

@Service
@Transactional(readOnly = false)
public class DistritoServiceImpl implements DistritoService{
	
	@Autowired
	DistritoDao distritoDao;

	@Override
	public void salvar(Distrito distrito) {
		distritoDao.save(distrito);
	}

	@Override
	public void editar(Distrito distrito) {
		distritoDao.update(distrito);
	}

	@Override
	public void excluir(Long id) {
		distritoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Distrito buscarPorId(Long id) {
		return distritoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Distrito> buscarTodos() {
		// TODO Auto-generated method stub
		return distritoDao.findAll();
	}

}
