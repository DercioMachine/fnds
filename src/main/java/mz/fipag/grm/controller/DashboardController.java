package mz.fipag.grm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.service.TipoOcorrenciaService;

@Controller
public class DashboardController {
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	TipoOcorrenciaService tipoOcorrenciaService;
	
	@ResponseBody
    @GetMapping("/buscarocorrencias")
    public String buscarOcorrencias(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.findAll());
    }
	
	
	@ResponseBody
    @GetMapping("/buscartiposocorrencias")
    public String buscarTiposOcorrencias(){

        Gson gson=new Gson();

        return gson.toJson(tipoOcorrenciaService.buscarTodos());
    }
}
