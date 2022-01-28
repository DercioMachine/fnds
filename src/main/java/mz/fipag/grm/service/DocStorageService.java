package mz.fipag.grm.service;

import java.util.List;
import java.util.Optional;

import mz.fipag.grm.domain.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.dao.DocRepository;
import mz.fipag.grm.domain.Doc;

@Service
public class DocStorageService {
	
	@Autowired
	private DocRepository docRepository;
	
	public Doc saveFile(MultipartFile file, Ocorrencia ocorrencia) {
		String docname = file.getOriginalFilename();
		String tipo = file.getContentType();
		

		try {
			byte[] dado = file.getBytes();
			
			Doc doc = new Doc();
			doc.setOcorrencia(ocorrencia);
			doc.setDocName(docname);
			doc.setFase("O");
			doc.setDocType(tipo);
			doc.setData(dado);
			
			return docRepository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Doc saveFileResolucao(MultipartFile file, Ocorrencia ocorrencia, String descricao,String fase) {
		String docname = file.getOriginalFilename();
		String tipo = file.getContentType();


		try {
			byte[] dado = file.getBytes();

			Doc doc = new Doc();
			doc.setOcorrencia(ocorrencia);
			doc.setDocName(docname);
			doc.setDescricao(descricao);
			doc.setFase(fase);
			doc.setDocType(tipo);
			doc.setData(dado);

			return docRepository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Doc> getFile(Long fileId){
		return docRepository.findById(fileId);
	}
	
	public List<Doc> getFiles(){
		return docRepository.findAll();
	}

}
