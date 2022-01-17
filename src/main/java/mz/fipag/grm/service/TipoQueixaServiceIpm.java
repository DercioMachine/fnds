package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.TipoQueixaDao;
import mz.fipag.grm.domain.TipoQueixa;

@Service
@Transactional(readOnly = false)
public class TipoQueixaServiceIpm implements TipoQueixaService{
	
	@Autowired
	TipoQueixaDao tipoQueixaDao;

	@Override
	public void salvar(TipoQueixa tipoQueixa) {
		tipoQueixaDao.save(tipoQueixa);
	}

	@Override
	public void editar(TipoQueixa tipoQueixa) {
		tipoQueixaDao.update(tipoQueixa);
	}

	@Override
	public void excluir(Long id) {
		tipoQueixaDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public TipoQueixa buscarPorId(Long id) {
		return tipoQueixaDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<TipoQueixa> buscarTodos() {
		return tipoQueixaDao.findAll();
	}

}
