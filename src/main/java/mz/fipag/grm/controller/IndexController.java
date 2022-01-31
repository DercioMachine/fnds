package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import mz.fipag.grm.domain.Ocorrencia;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "publico/principal";
    }

	/*
	 * @GetMapping("/login") public String homeIndex(){
	 * 
	 * return "login"; }
	 */

    @GetMapping("/home")
    public String home(){

        return "reclamacoes/home";
    }
    
   
    @GetMapping("/login")
    public String login(){

        return "login";
    }
    
    @GetMapping("/apresentar/preocupacao")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "publico/apresentarPreocupacao";
    }
    
}
