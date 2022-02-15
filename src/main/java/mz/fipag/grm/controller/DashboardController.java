package mz.fipag.grm.controller;

import java.util.Calendar;
import java.util.List;

import mz.fipag.grm.domain.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.CidadeService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.TipoOcorrenciaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	TipoOcorrenciaService tipoOcorrenciaService;
	
	@Autowired
	CategoriaService categoriaService;
	
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	ProjectoService projectoService;
	
	int ano=Calendar.getInstance().get(Calendar.YEAR);

    /* @GetMapping("/buscarocorrencias")
    public Iterable<Ocorrencia> buscarOcorrencias(){

      //  Gson gson=new Gson();
      //  return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));

        return  ocorrenciaRepository.buscarOcorrenciasActuais(ano);
    }  */

    @GetMapping("/buscarocorrencias")
    public List<Ocorrencia> buscarOcorrencias() {

     return   ocorrenciaRepository.buscarOcorrenciasActuais(ano);

    }
	
	@ResponseBody
    @GetMapping("/apiocorrenciascidades")
    public String buscarOcorrenciasCidades(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	@ResponseBody
    @GetMapping("/apicidades")
    public String apiCidades(){

        Gson gson=new Gson();

        return gson.toJson(cidadeService.buscarTodos());
    }
	
	@ResponseBody
    @GetMapping("/buscarocorrenciasforma")
    public String buscarOcorrenciasForma(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	
	@ResponseBody
    @GetMapping("/buscarocorrencias_teste")
    public String buscarOcorrenciasTeste(){

		Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	
	@ResponseBody
    @GetMapping("/buscarocoent")
    public String buscarEtnidade(){

		Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.buscarOcorrenciasActuais(ano));
    }
	
	@ResponseBody
    @GetMapping("/buscarcategorias")
    public String buscarCategorias(){

        Gson gson=new Gson();

        return gson.toJson(categoriaService.buscarTodos());
    }
	
	
	@ResponseBody
    @GetMapping("/buscarprojectos")
    public String buscarProjectos(){

        Gson gson=new Gson();

        return gson.toJson(projectoService.buscarTodos());
    }
	
	
	@ResponseBody
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
