package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TipoQueixaController {
	

	@GetMapping("/cadastrar")
	public String cadastro() {
		return "parametrizacao/tipo_queixa";
		
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "parametrizacao/lista";
		
	}
}
