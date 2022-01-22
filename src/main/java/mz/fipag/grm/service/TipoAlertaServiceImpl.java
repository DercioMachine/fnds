package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.TipoAlertaDao;
import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class TipoAlertaServiceImpl implements TipoAlertaService{
	
	@Autowired
	TipoAlertaDao tipoAlertaDao;

	@Override
	public void salvar(TipoAlerta tipoAlerta) {
		tipoAlertaDao.save(tipoAlerta);
	}

	@Override
	public void editar(TipoAlerta tipoAlerta) {
		tipoAlertaDao.update(tipoAlerta);
	}

	@Override
	public void excluir(Long id) {
		tipoAlertaDao.delete(id);
	}

	@Override 
	public TipoAlerta buscarPorId(Long id) {
		return tipoAlertaDao.findById(id);
	}

	@Override
	public List<TipoAlerta> buscarTodos() {
		return tipoAlertaDao.findAll();
	}

	@Override
	public PaginacaoUtil<TipoAlerta> buscaPorPagina(int pagina) {
		return tipoAlertaDao.buscaPaginada(pagina);
	}

}
