package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.TipoOcorrenciaDao;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class TipoOcorrenciaServiceImpl implements TipoOcorrenciaService{
	
	@Autowired
	TipoOcorrenciaDao tipoOcorrenciaDao;

	@Override
	public void salvar(TipoOcorrencia tipoOcorrencia) {
		tipoOcorrenciaDao.save(tipoOcorrencia);
	}

	@Override
	public void editar(TipoOcorrencia tipoOcorrencia) {
		tipoOcorrenciaDao.update(tipoOcorrencia);
	}

	@Override
	public void excluir(Long id) {
		tipoOcorrenciaDao.delete(id);
	}

	@Override
	public TipoOcorrencia buscarPorId(Long id) {
		return tipoOcorrenciaDao.findById(id);
	}

	@Override
	public List<TipoOcorrencia> buscarTodos() {
		return tipoOcorrenciaDao.findAll();
	}

	@Override
	public PaginacaoUtil<TipoOcorrencia> buscaPaginada(int pagina) {
		return tipoOcorrenciaDao.buscaPaginada(pagina);
	}

	
}
