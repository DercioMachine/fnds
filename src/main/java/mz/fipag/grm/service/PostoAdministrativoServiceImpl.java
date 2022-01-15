package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.PostoAdministrativoDao;
import mz.fipag.grm.domain.PostoAdministrativo;

@Service
@Transactional(readOnly = false)
public class PostoAdministrativoServiceImpl implements  PostoAdministrativoService{
	
	@Autowired
	PostoAdministrativoDao postoAdministrativoDao;

	@Override
	public void salvar(PostoAdministrativo postoAdministrativo) {
		postoAdministrativoDao.save(postoAdministrativo);
	}

	@Override
	public void editar(PostoAdministrativo postoAdministrativo) {
		postoAdministrativoDao.update(postoAdministrativo);
	}

	@Override
	public void excluir(Long id) {
		postoAdministrativoDao.delete(id);
	}

	@Override
	public PostoAdministrativo buscarPorId(Long id) {
		return postoAdministrativoDao.findById(id);
	}

	@Override
	public List<PostoAdministrativo> buscarTodos() {
		return postoAdministrativoDao.findAll();
	}

}
