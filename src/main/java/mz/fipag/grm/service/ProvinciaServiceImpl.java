package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.ProvinciaDao;
import mz.fipag.grm.domain.Provincia;

@Service
@Transactional(readOnly = false)
public class ProvinciaServiceImpl implements ProvinciaService{
	
	@Autowired
	ProvinciaDao provinciaDao;

	@Override
	public void salvar(Provincia provincia) {
		provinciaDao.save(provincia);
	}

	@Override
	public void editar(Provincia provincia) {
		provinciaDao.update(provincia);
	}

	@Override
	public void excluir(Long id) {
		provinciaDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Provincia buscarPorId(Long id) {
		return provinciaDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Provincia> buscarTodos() {
		return provinciaDao.findAll();
	}

}
