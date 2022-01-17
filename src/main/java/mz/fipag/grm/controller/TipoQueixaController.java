package mz.fipag.grm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.fipag.grm.domain.TipoQueixa;
import mz.fipag.grm.service.TipoQueixaService;

@Controller
public class TipoQueixaController {
	

	@Autowired
	TipoQueixaService tipoQueixaService;
	
	@GetMapping("/listar/tipoqueixa")
    public String listarTipoQueixa(ModelMap model){
		
		model.addAttribute("tipoqueixas", tipoQueixaService.buscarTodos());
		
        return "parametrizacao/tipoQueixa/listartipoqueixa";
    }
	
	  @GetMapping("/registar/tipoqueixa")
	    public String registarTipoQueixa(ModelMap model){
		  
		  model.addAttribute("tipoqueixa", new TipoQueixa());

	        return "parametrizacao/tipoQueixa/cadastrartipoqueixa";
	    }
	
	@PostMapping("/tipoqueixas/cadastrar")
	public String salvarTipoQueixa(TipoQueixa tipoQueixa, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		tipoQueixaService.salvar(tipoQueixa);
		attr.addFlashAttribute("success", "Tipo de Queixa inserido com sucesso.");
		return "redirect:/listar/tipoqueixa";
	}
	
	
	  @PostMapping("/tipoqueixas/editar") 
	  public String editarTipoQueixa(TipoQueixa tipoQueixa, RedirectAttributes attr) {
	  
		  tipoQueixaService.editar(tipoQueixa);
		  attr.addFlashAttribute("success", "Categoria editada com sucesso."); 
		  
		  return "redirect:/listar/tipoqueixa";
	  }
	 
	  
	  @GetMapping("/tipoqueixas/editar/{id}") public String
	  vistaEditarTipoQueixa(@PathVariable("id") Long id, ModelMap model) {
	  model.addAttribute("tipoqueixa", tipoQueixaService.buscarPorId(id));
	  return "parametrizacao/tipoQueixa/editartipoqueixa"; }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	  
	 
	
}
