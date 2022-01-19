package mz.fipag.grm.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.domain.Doc;

public interface DocDao {

	void save(Doc doc);
	
	void update(Doc doc);
	
	void delete (Long id);
	
	Doc findById(Long id);
	
	List<Doc> findAll();
	
	public Doc saveFile(MultipartFile file);
}
