package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

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

        return "ocorrencia/editarOcorrencia";
    }

    @GetMapping("/registar/ocorrencia")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "ocorrencia/cadastrarOcorrencia";
    }

}
