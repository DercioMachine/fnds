package mz.fipag.grm.controller;

import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.repository.ProvinciaRepository;
import mz.fipag.grm.repository.RegiaoRepository;
import mz.fipag.grm.service.JasperService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.TipoOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProjectoService projectoService;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @Autowired
    private TipoOcorrenciaService tpService;

    @Autowired
    private JasperService service;

    LocalDate currentdate = LocalDate.now();
    int currentYear = currentdate.getYear();

    int ano= Calendar.getInstance().get(Calendar.YEAR);

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

    @GetMapping("/relatorio/ocorrencia")
    public String relatorio(ModelMap model){
        model.addAttribute("ocorrencia", ocorrenciaRepository.ocorrenciasCorrentes(currentYear));
        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("projectos", projectoService.buscarTodos());
        model.addAttribute("tipoOcorrencias", tpService.buscarTodos());

        return "relatorio/ocorrencia";
    }

    @PostMapping("/ocorrencia/filtrar")
    public String filter1(@RequestParam("projecto") String projecto,
                          @RequestParam("provincia") String provincia,
                          @RequestParam("tipoOcorrencia") String tipoOcorrencia,
                          @RequestParam("estado") String estado,
                          @RequestParam(name="datainicial", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datainicial,
                          @RequestParam(name="datafinal", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datafinal,

                          Model model) {


        model.addAttribute("ocorrencia", ocorrenciaRepository.findAll());
        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("projectos", projectoService.buscarTodos());
        model.addAttribute("tipoOcorrencias", tpService.buscarTodos());

        service.addParams("projecto", projecto);
        service.addParams("data1", datainicial);
        service.addParams("data2", datafinal);
        service.addParams("cidade", provincia);
        service.addParams("tipoOcorrencia", tipoOcorrencia);
        service.addParams("estado", estado);

        model.addAttribute("ocorrencia", ocorrenciaRepository.totalDeOcorrenciasPorDataseProjectoRelatorio(datainicial,datafinal,projecto,provincia,estado,tipoOcorrencia));


        return "relatorio/ocorrencia";

    }

}
