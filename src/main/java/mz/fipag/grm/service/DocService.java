package mz.fipag.grm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.domain.Doc;

public interface DocService {

	void salvar (Doc doc);
	
	void editar (Doc doc);
	
	void excluir (Long id);
	
	Doc buscarPorId(Long id);
	
	List<Doc> buscarTodos();
	
	public Doc saveFile(MultipartFile file);
	
}
