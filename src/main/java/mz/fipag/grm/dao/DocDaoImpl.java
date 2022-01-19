package mz.fipag.grm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.domain.Doc;

@Repository
public class DocDaoImpl extends AbstractDao<Doc, Long>implements DocDao{

	@Override
	public Doc saveFile(MultipartFile file) {
		
		
		
		return null;
	}

	

}
