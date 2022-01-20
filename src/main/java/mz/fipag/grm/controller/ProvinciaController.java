package mz.fipag.grm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.service.ProvinciaService;

@Controller
public class ProvinciaController {
	
	@Autowired
	private ProvinciaService provinciaService;
	
	public List<Provincia> getAll(){
		return provinciaService.buscarTodos();
		
	}

}
