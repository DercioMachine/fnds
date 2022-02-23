package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.domain.SubCategoria;
import mz.fipag.grm.repository.SubCategoriaRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class SubCategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@GetMapping("/listar/sucategorias")
    public String sucategorias(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<Categoria> pageCategoria = categoriaService.buscaPorPagina(paginaActual);
		
		model.addAttribute("subcategorias", subCategoriaRepository.findAll());
		
		//model.addAttribute("pageCategoria", pageCategoria);
		
		
        return "parametrizacao/subcategoria/listar";
    }
	
	  @GetMapping("/registar/subcategoria")
	    public String registarCategoria(ModelMap model){
		  
		  model.addAttribute("subcategoria", new SubCategoria());
		  model.addAttribute("categorias", categoriaService.buscarTodos());

	        return "parametrizacao/subcategoria/cadastrar";
	    }
	
	@PostMapping("/subcategoria/cadastrar")
	public String salvarCategoria(SubCategoria subcategoria, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		subCategoriaRepository.save(subcategoria);
		attr.addFlashAttribute("success", "Posto Administrativo inserido com sucesso.");
		return "redirect:/listar/sucategorias";
	}
	
	
	  @PostMapping("/subcategoria/editar")
	  public String editarCategoria(SubCategoria subcategoria, RedirectAttributes attr) {

		  subCategoriaRepository.save(subcategoria);
		  
		  attr.addFlashAttribute("success","Categoria editada com sucesso."); 
		  
		  return "redirect:/listar/sucategorias";
	  }
	 
	  
	  @GetMapping("/subcategoria/editar/{id}")
	  public String vistaEditarCategoria(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("subcategoria", subCategoriaRepository.findById(id));
	  model.addAttribute("categorias", categoriaService.buscarTodos());

	  
	  return "parametrizacao/subcategoria/editar";
	  }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
