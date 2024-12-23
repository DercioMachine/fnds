package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegiaoController {


    @Autowired
    private RegiaoRepository regiaoRepository;

    @GetMapping("/listar/regioes")
    public String listarRegiao(ModelMap model){

        model.addAttribute("regioes", regiaoRepository.findAll());

        return "parametrizacao/regiao/listarRegiao";
    }

    /*@GetMapping("/cadastrar/regiao")
    public String vistaRegiao(ModelMap model){

       model.addAttribute("cidades", cidadeService.buscarTodos());
       model.addAttribute("regiao", new Regiao());

        return "parametrizacao/regiao/cadastrarRegiao";
    }*/

    @PostMapping("/regiao/cadastrar")
    public String gravarRegiao(Regiao regiao) {

        regiaoRepository.save(regiao);

        return "redirect:/listar/regioes";
    }

}
