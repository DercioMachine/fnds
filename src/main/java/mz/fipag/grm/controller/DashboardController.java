package mz.fipag.grm.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.TipoOcorrenciaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	TipoOcorrenciaService tipoOcorrenciaService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProjectoService projectoService;
	
	int ano=Calendar.getInstance().get(Calendar.YEAR);

    /* @GetMapping("/buscarocorrencias")
    public Iterable<Ocorrencia> buscarOcorrencias(){

      //  Gson gson=new Gson();
      //  return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));

        return  ocorrenciaRepository.buscarOcorrenciasActuais(ano);
    }  */

	@ResponseBody
    @GetMapping("/buscarocorrencias")
    public String buscarOcorrencias() {

    	Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));

    }
	
    @GetMapping("/apiocorrenciascidades")
    public String buscarOcorrenciasCidades(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
    /*@GetMapping("/apicidades")
    public String apiCidades(){

        Gson gson=new Gson();

        return gson.toJson(cidadeService.buscarTodos());
    }*/
	
    @GetMapping("/buscarocorrenciasforma")
    public String buscarOcorrenciasForma(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	
    @GetMapping("/buscarocorrencias_teste")
    public String buscarOcorrenciasTeste(){

		Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	
    @GetMapping("/buscarocoent")
    public String buscarEtnidade(){

		Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
    @GetMapping("/buscarcategorias")
    public String buscarCategorias(){

        Gson gson=new Gson();

        return gson.toJson(categoriaService.buscarTodos());
    }
	
	
    @GetMapping("/buscarprojectos")
    public String buscarProjectos(){

        Gson gson=new Gson();

        return gson.toJson(projectoService.buscarTodos());
    }
	
	
    @GetMapping("/apiocorrencias")
    public String buscarOcorrenciass(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/apiococate") public String
	 * buscarOcorrenciasAgrupadasPorCategoria(){
	 * 
	 * Gson gson=new Gson();
	 * 
	 * return gson.toJson(ocorrenciaRepository.busqueTudoAgrupadoPorCategoria()); }
	 */
	
	
	@ResponseBody
    @GetMapping("/buscartiposocorrencias")
    public String buscarTiposOcorrencias(){

        Gson gson=new Gson();

        return gson.toJson(tipoOcorrenciaService.buscarTodos());
    }
	
}
