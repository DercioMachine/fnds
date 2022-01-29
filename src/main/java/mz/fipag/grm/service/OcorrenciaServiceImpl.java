package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.OcorrenciaDao;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class OcorrenciaServiceImpl implements OcorrenciaService{
	
	@Autowired
	OcorrenciaDao ocorrenciaDao;

	@Override
	public void salvar(Ocorrencia ocorrencia) {
		ocorrenciaDao.save(ocorrencia);
	}

	@Override
	public void editar(Ocorrencia ocorrencia) {
		ocorrenciaDao.update(ocorrencia);
	}


	@Override
	public void excluir(Long id) {
		ocorrenciaDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Ocorrencia buscarPorId(Long id) {
		return ocorrenciaDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Ocorrencia> buscarTodos() {
		return ocorrenciaDao.findAll();
	}

	@Override
	public PaginacaoUtil<Ocorrencia> buscaPorPagina(int pagina) {
		return ocorrenciaDao.buscaPaginada(pagina);
	}

}
