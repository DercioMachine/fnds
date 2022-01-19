package mz.fipag.grm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.dao.DocDao;
import mz.fipag.grm.domain.Doc;

@Service
@Transactional(readOnly = false)
public class DocServiceImpl implements DocService{

	@Autowired
	DocDao docDao;
	
	@Override
	public void salvar(Doc doc) {
		docDao.save(doc);
	}

	@Override
	public void editar(Doc doc) {
		docDao.update(doc);
	}

	@Override 
	public void excluir(Long id) {
		docDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Doc buscarPorId(Long id) {
		return docDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Doc> buscarTodos() {
		return docDao.findAll();
	}

	@Override
	public Doc saveFile(MultipartFile file) {
		String docname = file.getOriginalFilename();
		
		try {
			Doc doc = new Doc();
			doc.setDocName(docname);
			return docDao.saveFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docDao.saveFile(file);
		
		
		
	}

}
