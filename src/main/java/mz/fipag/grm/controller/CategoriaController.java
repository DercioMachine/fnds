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
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listar/categoria")
    public String listarCategoria(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<Categoria> pageCategoria = categoriaService.buscaPorPagina(paginaActual);
		
		//model.addAttribute("categorias", categoriaService.buscarTodos());
		
		model.addAttribute("pageCategoria", pageCategoria);
		
		
        return "parametrizacao/categoria/listarcategoria";
    }
	
	  @GetMapping("/registar/categoria")
	    public String registarCategoria(ModelMap model){
		  
		  model.addAttribute("categoria", new Categoria());

	        return "parametrizacao/categoria/cadastrarcategoria";
	    }
	
	@PostMapping("/categorias/cadastrar")
	public String salvarCategoria(Categoria categoria, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		categoriaService.salvar(categoria);
		attr.addFlashAttribute("success", "Posto Administrativo inserido com sucesso.");
		return "redirect:/listar/categoria";
	}
	
	
	  @PostMapping("/categorias/editar") 
	  public String editarCategoria(Categoria categoria, RedirectAttributes attr) {
	  
		  categoriaService.editar(categoria);
		  
		  attr.addFlashAttribute("success","Categoria editada com sucesso."); 
		  
		  return "redirect:/listar/categoria"; 
	  }
	 
	  
	  @GetMapping("/categorias/editar/{id}") 
	  public String vistaEditarCategoria(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("categoria", categoriaService.buscarPorId(id));
	  
	  return "parametrizacao/categoria/editarcategoria"; 
	  }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
