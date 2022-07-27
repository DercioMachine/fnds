package mz.fipag.grm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class PostoAdministrativoController {
	
	@Autowired
	private PostoAdministrativoService postoAdministrativoService;
	
	@GetMapping("/listar/posto")
    public String listarReclmacao(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<PostoAdministrativo> pagePosto = postoAdministrativoService.buscaPorPagina(paginaActual);
		
		model.addAttribute("pagePosto", pagePosto);
		//model.addAttribute("postos", postoAdministrativoService.buscarTodos());
		
        return "parametrizacao/postoAdministrativo/listarposto";
    }
	
	  @GetMapping("/registar/posto")
	    public String registarPosto(ModelMap model){
		  
		  model.addAttribute("posto", new PostoAdministrativo());

	        return "parametrizacao/postoAdministrativo/cadastrarposto";
	    }
	
	@PostMapping("/postos/cadastrar")
	public String salvar(PostoAdministrativo postoAdministrativo, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		postoAdministrativoService.salvar(postoAdministrativo);
		//attr.addFlashAttribute("success", "Posto Administrativo inserido com sucesso.");
		return "redirect:/listar/posto";
	}
	
	@PostMapping("/postos/editar")
	public String editarPosto(PostoAdministrativo postoAdministrativo, RedirectAttributes attr) {
		
		postoAdministrativoService.editar(postoAdministrativo);
		//attr.addFlashAttribute("success", "Posto Administrativo editado com sucesso.");
		return "redirect:/listar/posto";
	}

	@GetMapping("/postos/editar/{id}")
	public String vistaEditarPosto(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("posto", postoAdministrativoService.buscarPorId(id));
		 return "parametrizacao/postoAdministrativo/editarposto";
	}
	
	@GetMapping("/postos/excluir/{id}")
	public String vistaExcluirPosto(@PathVariable("id") Long id, ModelMap model) {
		postoAdministrativoService.excluir(id);
		return "redirect:/listar/posto";
	}
	

}
