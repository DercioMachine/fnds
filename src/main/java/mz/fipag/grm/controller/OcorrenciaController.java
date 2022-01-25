package mz.fipag.grm.controller;

import java.util.List;

import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.repository.DistritoRepository;
import mz.fipag.grm.repository.PostoAdminitrativoRepository;

@Controller
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;
    
    @Autowired
    private ProvinciaService provinciaService;
    
    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private DocsRepository docsRepository;


    @Autowired
    private PostoAdminitrativoRepository postoAdminitrativoRepository;

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private TipoAlertaService tipoAlertaService;
    
    @Autowired
    private PostoAdministrativoService postoAdminService;
    
    @Autowired
    private TipoOcorrenciaService tipoDeOcorrenciasService;

    @Autowired
    private DocStorageService docStorageService;


    @GetMapping("/listar/ocorrencia")
    public String listarOcorrencia(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/listarOcorrencia";
    }
    
    
    @GetMapping("/listar/teste")
    public String litarTeste(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/teste";
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

    @ResponseBody
    @GetMapping("/distrito/{id}")
    public String listarDistrito(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(distritoRepository.findAllById(id));
    }


    @ResponseBody
    @GetMapping("/posto/{id}")
    public String listarPosto(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(postoAdminitrativoRepository.findAllById(id));
    }




    @PostMapping("/ocorrencias/cadastrar")
	public String salvarOcorrencia(Ocorrencia ocorrencia, @RequestParam("files") MultipartFile[] files) {

    	
    	ocorrencia.setRegistado(true);
    	ocorrencia.setEstado("Registado");
    	ocorrenciaService.salvar(ocorrencia);

    	for(MultipartFile file: files) {
            docStorageService.saveFile(file, ocorrencia);
        }

    	return "redirect:/listar/ocorrencia";
	}
    
    @PostMapping("/ocorrencias/listar")
   	public String verOcorrencias(Ocorrencia ocorrencia) {

   		/*
   		 * if (result.hasErrors()) { return "cargo/cadastro"; }
   		 */
       //	ocorrenciaService.salvar(ocorrencia);
       	return "redirect:/listar/ocorrencia";
   	}

    @PostMapping("/ocorrencias/editar") 
	  public String editarCategoria(Ocorrencia ocorrencia) {
	  
    	ocorrencia.setRegistado(true);
    	ocorrencia.setEstado("Registado");
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
    	 model.addAttribute("anexos", docsRepository.findAllById(id));

        return "ocorrencia/detalharOcorrencia";

    }
    
    /*
	 *  VALIDACAO
	 *
	 */

    @PostMapping("/ocorrencias/validar")
    public String validacaoVista(Ocorrencia ocorrencia,  RedirectAttributes redirAttrs) {
    	
    	ocorrencia.setValidado(true);
    	ocorrencia.setEstado("Validado");
        ocorrenciaService.editar(ocorrencia);

        return "redirect:/listar/ocorrencia";

    }

    @GetMapping("/ocorrencias/validar/{id}")
    public String validacaoAccao (@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));

        return "ocorrencia/registarValidacao";
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
    
    @ModelAttribute("tipoAlertas")
    	public List<TipoAlerta> listaDetipoDeAlertas(){
    		return tipoAlertaService.buscarTodos();
    	}




}
