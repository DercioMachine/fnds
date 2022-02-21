package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.fipag.grm.dao.OperacaoDao;
import mz.fipag.grm.domain.Operacao;
import mz.fipag.grm.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class OperacaoServiceImpl implements OperacaoService{
	
	@Autowired
	OperacaoDao operacaoDao;

	@Override
	public void salvar(Operacao operacao) {
		// TODO Auto-generated method stub
		operacaoDao.save(operacao);
	}

	@Override
	public void editar(Operacao operacao) {
		// TODO Auto-generated method stub
		operacaoDao.update(operacao);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		operacaoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Operacao buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return operacaoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Operacao> buscarTodos() {
		// TODO Auto-generated method stub
		return operacaoDao.findAll();
	}

	@Override
	public PaginacaoUtil<Operacao> buscaPorPagina(int pagina) {
		// TODO Auto-generated method stub
		return operacaoDao.buscaPaginada(pagina);
	}

	

}
