package mz.fipag.grm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/ocorrencia/fase2")
    public String ocorrenciaFase2(Ocorrencia ocorrencia, RedirectAttributes attr) {

        ocorrenciaService.editar(ocorrencia);
        attr.addFlashAttribute("success","Ocorrencia fase 2 registada com sucesso.");

        return "redirect:/listar/ocorrencia";
    }

    @PostMapping("/ocorrencia/cadastrar")
    public String salvarOcorrencia(Ocorrencia ocorrencia, ModelMap model) {

        ocorrenciaService.salvar(ocorrencia);

        model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(ocorrencia.getId()));

        //return "ocorrencia/editarOcorrencia";
        return "redirect:/listar/ocorrencia";
    }

    @GetMapping("/registar/ocorrencia")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "ocorrencia/cadastrarOcorrencia";
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
