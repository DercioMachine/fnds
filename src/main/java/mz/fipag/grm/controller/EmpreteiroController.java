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

import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.service.EmpreiterioService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class EmpreteiroController {
	
	@Autowired
	private EmpreiterioService empreiteiroService;
	
	@GetMapping("/listar/empreiteiro")
    public String listarEmpreiteiro(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<Empreiteiro> pageEmpreiteiro = empreiteiroService.buscaPorPagina(paginaActual);
		
		//model.addAttribute("categorias", categoriaService.buscarTodos());
		
		model.addAttribute("pageEmpreiteiro", pageEmpreiteiro);
		
		
        return "parametrizacao/empreiteiro/listar";
    }
	
	  @GetMapping("/registar/empreiteiro")
	    public String registarEmpreiteiro(ModelMap model){
		  
		  model.addAttribute("empreiteiro", new Empreiteiro());

		  return "parametrizacao/empreiteiro/cadastrar";
	    }
	
	@PostMapping("/empreiteiros/cadastrar")
	public String salvarEmpreiteiro(Empreiteiro empreiteiro, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		empreiteiroService.salvar(empreiteiro);
		attr.addFlashAttribute("success", "Empreiteiro Administrativo inserido com sucesso.");
		return "redirect:/listar/empreiteiro";
	}
	
	
	  @PostMapping("/empreiteiros/editar") 
	  public String editarEmpreiteiro(Empreiteiro empreiteiro, RedirectAttributes attr) {
	  
		  empreiteiroService.editar(empreiteiro);
		  
		  attr.addFlashAttribute("success","Empreiteiro editada com sucesso."); 
		  
		  return "redirect:/listar/empreiteiro"; 
	  }
	 
	  
	  @GetMapping("/empreiteiros/editar/{id}") 
	  public String vistaEditarEmpreiteiro(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("empreiteiro", empreiteiroService.buscarPorId(id));
	  
	  return "parametrizacao/empreiteiro/editar";
	  }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
