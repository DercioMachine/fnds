package mz.fipag.grm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.service.DistritoService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.service.TipoOcorrenciaService;

@Controller
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;
    
    @Autowired
    private ProvinciaService provinciaService;
    
    @Autowired
    private DistritoService distritoService;
    
    @Autowired
    private PostoAdministrativoService postoAdminService;
    
    @Autowired
    private TipoOcorrenciaService tipoDeOcorrenciasService;
    

    @GetMapping("/listar/ocorrencia")
    public String listarOcorrencia(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/listarOcorrencia";
    }
    
    @GetMapping("/listar/ocorrencia2")
    public String listarOcorrencia2(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/listarOcorrencia2";
    }
    
    @GetMapping("/registar/ocorrencia")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "ocorrencia/registarOcorrenciaCompleta";
    }

    

    @PostMapping("/ocorrencias/cadastrar")
	public String salvarOcorrencia(Ocorrencia ocorrencia) {
		
		/*
		 * if (result.hasErrors()) { return "cargo/cadastro"; }
		 */
    	ocorrenciaService.salvar(ocorrencia);
    	return "redirect:/listar/ocorrencia";
	}
    
    
    @PostMapping("/ocorrencias/editar") 
	  public String editarCategoria(Ocorrencia ocorrencia) {
	  
    	ocorrenciaService.editar(ocorrencia);
		  
		  
		  return "redirect:/listar/ocorrencia"; 
	  }
    
    @GetMapping("/ocorrencias/editar/{id}") 
	  public String vistaEditarOcorrencia(@PathVariable("id") Long id, ModelMap model) {
	  
    	model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
	  
	  return "ocorrencia/editarOcorrencia"; 
	  }
    
    
    @PostMapping("/ocorrencias/fase2")
    public String editarOcorrenciaFase2Vista(Ocorrencia ocorrencia) {

        ocorrenciaService.editar(ocorrencia);
        
        return "redirect:/listar/ocorrencia2";
    }
    
    @GetMapping("/ocorrencias/fase2/{id}")
    public String editarOcorrenciaFase2(@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
		
        return "ocorrencia/editarOcorrencia";
    }

    @GetMapping("/listar/detalhes")
    public String listarDetalhes(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/detalharOcorrencia";
    }
    
    @PostMapping("/ocorrencias/detalhar")
    public String detalhesVista(Ocorrencia ocorrencia) {

        ocorrenciaService.editar(ocorrencia);
        
        return "redirect:/listar/detalhes";
    }
    
    @GetMapping("/ocorrencias/detalhar/{id}")
    public String detalhesVistaAccao (@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
        
        return "ocorrencia/detalharOcorrencia";
    }
    
    @ModelAttribute("provincias")
	public List<Provincia> listaDeDePronvicias() {
		return provinciaService.buscarTodos();
	}	
    
    @ModelAttribute("distritos")
	public List<Distrito> listaDeDistritos() {
		return distritoService.buscarTodos();
	}	
    
    @ModelAttribute("postos")
	public List<PostoAdministrativo> listaDePostos() {
		return postoAdminService.buscarTodos();
	}
    
    @ModelAttribute("tipoDeOcorrencias")
	public List<TipoOcorrencia> listaDetipoDeOcorrencias() {
		return tipoDeOcorrenciasService.buscarTodos();
	}
    
    

}
