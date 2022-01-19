package mz.fipag.grm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.dao.DocRepository;
import mz.fipag.grm.domain.Doc;

@Service
public class DocStorageService {
	
	@Autowired
	private DocRepository docRepository;
	
	public Doc saveFile(MultipartFile file) {
		String docname = file.getOriginalFilename();
		String tipo = file.getContentType();
		

		try {
			byte[] dado = file.getBytes();
			
			Doc doc = new Doc();
			doc.setDocName(docname);
			doc.setDocType(tipo);
			doc.setData(dado);
			
			return docRepository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Doc> getFile(Integer fileId){
		return docRepository.findById(fileId);
	}
	
	public List<Doc> getFiles(){
		return docRepository.findAll();
	}

}
