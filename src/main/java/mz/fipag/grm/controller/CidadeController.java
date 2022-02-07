package mz.fipag.grm.controller;

import java.util.List;
import java.util.Optional;

import mz.fipag.grm.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.service.CidadeService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private ProvinciaService provinciaService;

	@Autowired
	private RegiaoRepository regiaoRepository;
	
	@GetMapping("/listar/cidade")
    public String listarCidade(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<Cidade> pageCidade = cidadeService.buscaPorPagina(paginaActual);
		
		//model.addAttribute("categorias", categoriaService.buscarTodos());
		
		model.addAttribute("pageCidade", pageCidade);
		
		
        return "parametrizacao/cidade/listar";
    }
	
	  @GetMapping("/registar/cidade")
	    public String registarCidade(ModelMap model){
		  
		  model.addAttribute("cidade", new Cidade());
		  model.addAttribute("regioes", regiaoRepository.findAll());

		  return "parametrizacao/cidade/cadastrar";
	    }
	
	@PostMapping("/cidades/cadastrar")
	public String salvarCidade(Cidade cidade, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		cidadeService.salvar(cidade);
		attr.addFlashAttribute("success", "Cidade inserido com sucesso.");
		return "redirect:/listar/cidade";
	}
	
	
	  @PostMapping("/cidades/editar") 
	  public String editarCidade(Cidade cidade, RedirectAttributes attr) {
	  
		  cidadeService.editar(cidade);
		  
		  attr.addFlashAttribute("success","Cidade editada com sucesso."); 
		  
		  return "redirect:/listar/cidade"; 
	  }
	 
	  
	  @GetMapping("/cidades/editar/{id}") 
	  public String vistaEditarEmpreiteiro(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("cidade", cidadeService.buscarPorId(id));
	  
	  return "parametrizacao/cidade/editar";
	  }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	  
	  @ModelAttribute("provincias")
		public List<Provincia> listaDeDePronvicias() {
			return provinciaService.buscarTodos();
		}	
	 
	

}
