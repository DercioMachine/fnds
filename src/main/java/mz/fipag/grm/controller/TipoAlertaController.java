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

import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.service.TipoAlertaService;
import mz.fipag.grm.util.PaginacaoUtil;

@Controller
public class TipoAlertaController {
	
	@Autowired
	private TipoAlertaService tipoAlertaService;
	
	@GetMapping("/listar/tipoAlerta")
    public String listarTipoAlerta(ModelMap model, @RequestParam("page") Optional<Integer> page){
		
		int paginaActual = page.orElse(1);
		PaginacaoUtil<TipoAlerta> pageTipoAlerta = tipoAlertaService.buscaPorPagina(paginaActual);
		
		model.addAttribute("pageTipoAlerta", pageTipoAlerta);
		
		
        return "parametrizacao/tipoAlerta/listar";
    }
	
	  @GetMapping("/registar/tipoAlerta")
	    public String registarTipoAlerta(ModelMap model){
		  
		  model.addAttribute("tipoAlerta", new TipoAlerta());

	        return "parametrizacao/tipoAlerta/cadastrar";
	    }
	
	@PostMapping("/tipoAlertas/cadastrar")
	public String salvarTipoAlerta(TipoAlerta tipoAlerta, RedirectAttributes attr) {
		
		tipoAlertaService.salvar(tipoAlerta);
		attr.addFlashAttribute("success", "Posto Administrativo inserido com sucesso.");
		return "redirect:/listar/tipoAlerta";
	}
	
	
	  @PostMapping("/tipoAlertas/editar") 
	  public String editarTipoAlerta(TipoAlerta tipoAlerta, RedirectAttributes attr) {
	  
		  tipoAlertaService.editar(tipoAlerta);
		  
		  attr.addFlashAttribute("success","Tipo de Alerta editada com sucesso."); 
		  
		  return "redirect:/listar/tipoAlerta"; 
	  }
	 
	  
	  @GetMapping("/tipoAlertas/editar/{id}") 
	  public String vistaEditarTipoAlerta(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("tipoAlerta", tipoAlertaService.buscarPorId(id));
	  
	  return "parametrizacao/tipoAlerta/editar"; 
	  }
	  
	  /*@GetMapping("/categorias/excluir/{id}") public String
	  vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  categoriaService.excluir(id); 
		  return "redirect:/listar/categoria"; 
		  
	  }*/
	 
	

}
