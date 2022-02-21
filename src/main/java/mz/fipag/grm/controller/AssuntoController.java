package mz.fipag.grm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mz.fipag.grm.domain.Assunto;
import mz.fipag.grm.repository.AssuntoRepository;

@Controller
public class AssuntoController {

    
    @Autowired
    private AssuntoRepository assuntoRepository;

    @GetMapping("/listar/assuntos")
    public String listarRegiao(ModelMap model){

        model.addAttribute("assuntos", assuntoRepository.findAll());

        return "parametrizacao/assunto/listar";
    }

    @GetMapping("/cadastrar/assunto")
    public String vistaRegiao(ModelMap model){

       model.addAttribute("assunto", new Assunto());

        return "parametrizacao/assunto/cadastrar";
    }

    @PostMapping("/assunto/cadastrar")
    public String gravar(Assunto assunto) {

    	assuntoRepository.save(assunto);

        return "redirect:/listar/assuntos";
    }
    
    @PostMapping("/editar/assunto") 
	  public String editar(Assunto assunto) {
	  
    	assuntoRepository.save(assunto);
		  
		  
		  return "redirect:/listar/assuntos"; 
	  }
	 
	  
	  @GetMapping("/editar/assunto/{id}") 
	  public String vistaEditar(@PathVariable("id") Long id, ModelMap model) {
		  
	  model.addAttribute("assunto", assuntoRepository.findById(id));
	  
	  return "parametrizacao/assunto/editar"; 
	  }

}
