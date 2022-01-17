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

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class ProjectoController {
	
	@Autowired
	private ProjectoService projectoService;
	
	@GetMapping("/listar/projecto")
    public String listarProjecto(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<Projecto> pageProjecto = projectoService.buscaPorPagina(paginaActual);
		
		//model.addAttribute("projectos", projectoService.buscarTodos());
		
		model.addAttribute("pageProjecto", pageProjecto);
		
        return "parametrizacao/projecto/listarprojecto";
    }
	
	  @GetMapping("/registar/projecto")
	    public String registarProjecto(ModelMap model){
		  
		  model.addAttribute("projecto", new Categoria());

	        return "parametrizacao/projecto/cadastrarprojecto";
	    }
	
	@PostMapping("/projectos/cadastrar")
	public String salvarProjecto(Projecto projecto, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		projectoService.salvar(projecto);
		attr.addFlashAttribute("success", "Projecto inserido com sucesso.");
		return "redirect:/listar/projecto";
	}
	
	
	  @PostMapping("/projectos/editar") public String editarProjecto (Projecto projecto, RedirectAttributes attr) {
	  
		  projectoService.editar(projecto);
		  attr.addFlashAttribute("success", "projecto editado com sucesso."); 
		  
		  return "redirect:/listar/projecto"; 
	  }
	 
	  
	  @GetMapping("/projectos/editar/{id}") 
	  public String vistaEditarProjecto(@PathVariable("id") Long id, ModelMap model) {
	  model.addAttribute("projecto", projectoService.buscarPorId(id));
	  return "parametrizacao/projecto/editarprojecto"; }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
