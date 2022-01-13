package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/registar/reclamacao")
    public String registarReclamacao(){

        return "reclamacoes/registarreclamacao";
    }
    @GetMapping("/reclamacao/fase2")
    public String registarReclamacaoFase2(){

        return "reclamacoes/registarreclamacaof2";
    }

    @GetMapping("/listar/reclamacao")
    public String listarReclmacao(){

        return "reclamacoes/listarreclamacao";
    }

}
