package mz.fipag.grm.controller;

import com.google.gson.Gson;
import mz.fipag.grm.domain.Origem;
import mz.fipag.grm.domain.Processo;
import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.repository.OrigemRepository;
import mz.fipag.grm.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProcessoController {


    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private OrigemRepository origemRepository;

    @GetMapping("/listar/processos")
    public String listarProcesso(Model model){

        model.addAttribute("processos", processoRepository.findAll());

        return "parametrizacao/origem/listarProcesso";
    }

    @GetMapping("/registar/processos")
    public String viewProcesso(ModelMap model){

        model.addAttribute("processo", new Processo());

        return "parametrizacao/origem/cadastrarProcesso";
    }

    @PostMapping("/cadastrar/processo")
    public String gravarProcesso(Processo processo) {

        processoRepository.save(processo);

        return "redirect:/listar/processos";
    }

    @GetMapping("/listar/origem")
    public String listarOrigem(Model model){

        model.addAttribute("origens", origemRepository.findAll());

        return "parametrizacao/origem/listarOrigem";
    }

    @GetMapping("/registar/origem")
    public String viewOrigem(ModelMap model){

        model.addAttribute("origem", new Origem());

        return "parametrizacao/origem/cadastrarOrigem";
    }

    @ResponseBody
    @GetMapping("/origem/interno")
    public String apiOrigemInterno(){

        Gson gson=new Gson();

        return gson.toJson(origemRepository.findAllInterno());
    }

    @ResponseBody
    @GetMapping("/origem/externo")
    public String apiOrigemExterno(){

        Gson gson=new Gson();

        return gson.toJson(origemRepository.findAll());
    }

    @PostMapping("/cadastrar/origem")
    public String gravarOrigem(Origem origem) {

        origemRepository.save(origem);

        return "redirect:/listar/origem";
    }


}
