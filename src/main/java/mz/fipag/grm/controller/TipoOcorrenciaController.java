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

import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.service.TipoOcorrenciaService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class TipoOcorrenciaController {
	
	@Autowired
	private TipoOcorrenciaService tipoOcorrenciaService;
	
	@GetMapping("/listar/tipoOcorrencia")
    public String listarTipoOcorrencia(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<TipoOcorrencia> pageTipoOcorrencia = tipoOcorrenciaService.buscaPaginada(paginaActual);
		
		//model.addAttribute("projectos", projectoService.buscarTodos());
		
		model.addAttribute("pageTipoOcorrencia", pageTipoOcorrencia);
		
        return "parametrizacao/tipoOcorrencia/listar";
    }
	
	  @GetMapping("/registar/tipoOcorrencia")
	    public String registarTipoOcorrencia(ModelMap model){
		  
		  model.addAttribute("tipoOcorrencia", new TipoOcorrencia());

	        return "parametrizacao/tipoOcorrencia/cadastrar";
	    }
	
	@PostMapping("/tipoOcorrencias/cadastrar")
	public String salvarTipoOcorrencia(TipoOcorrencia tipoOcorrencia, RedirectAttributes attr) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
		tipoOcorrenciaService.salvar(tipoOcorrencia);
		attr.addFlashAttribute("success", "Tipo de Ocorrencia inserido com sucesso.");
		return "redirect:/listar/tipoOcorrencia";
	}
	
	
	  @PostMapping("/tipoOcorrencias/editar") public String editarTipoOcorrencia (TipoOcorrencia tipoOcorrencia, RedirectAttributes attr) {
	  
		 tipoOcorrenciaService.editar(tipoOcorrencia);
		  attr.addFlashAttribute("success", "Tipo de Ocorrencia editado com sucesso."); 
		  
		  return "redirect:/listar/tipoOcorrencia"; 
	  }
	 
	  
	  @GetMapping("/tipoOcorrencias/editar/{id}") 
	  public String vistaEditarTipoOcorrencia(@PathVariable("id") Long id, ModelMap model) {
	  model.addAttribute("tipoOcorrencia", tipoOcorrenciaService.buscarPorId(id));
	  return "parametrizacao/tipoOcorrencia/editar"; }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
