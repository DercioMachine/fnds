package mz.fipag.grm.controller;

import java.util.List;

import mz.fipag.grm.domain.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mz.fipag.grm.domain.Doc;
import mz.fipag.grm.service.DocStorageService;

@Controller
public class DocController {

	@Autowired
	private DocStorageService docStorageService;

	//@GetMapping("/")
	@GetMapping("/listar/ficheiro")
	public String get(Model model) {
		List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		return "ocorrencia/cadastrarOcorrencia";
		
	}
	
	//@PostMapping("/uploadFiles")
	@PostMapping("/upload/ficheiro")
	public String uploadMultipleFiles (@RequestParam("files") MultipartFile[] files, Ocorrencia ocorrencia,@RequestParam("descricao") String descricao) {
		for(MultipartFile file: files) {
			docStorageService.saveFile(file, ocorrencia, descricao);
		}
		return "redirect:/listar/ficheiro";
	}
	
	//@GetMapping("/downloadFile/{fileId}")
	@GetMapping("/download/ficheiro/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
		
	}
	
}
