package mz.fipag.grm.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.repository.ProvinciaRepository;
import mz.fipag.grm.repository.RegiaoRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.JasperService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.TipoOcorrenciaService;

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
    private CategoriaService catService;
    
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


    /*****************************************GRAFICOS DE OCORRENCIAS****************************************/



    public void projecto(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProjecto(currentYear);


        String[] legendaestado = new String[lista.size()];

        BigDecimal[] nrocorrencias = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias2 = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias3 = new BigDecimal[lista.size()];



        int i=0;
        for (Object[] ob : lista){

            legendaestado[i] = (String) ob[0];
            nrocorrencias[i] = (BigDecimal) ob[1];
            nrocorrencias2[i] = (BigDecimal) ob[2];
            nrocorrencias3[i] = (BigDecimal) ob[3];



            i++;
        }


        model.addAttribute("nomesProjecto",legendaestado);
        model.addAttribute("numeroocorenciaProjecto", nrocorrencias);
        model.addAttribute("numeroocorenciaProjecto2", nrocorrencias2);
        model.addAttribute("numeroocorenciaProjecto3", nrocorrencias3);



    }



    public void cidade(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCidade();


        String[] nomes = new String[lista.size()];
        BigInteger[] nrocorrencias = new BigInteger[lista.size()];
        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];
            nrocorrencias[i] = (BigInteger) ob[1];

            i++;
        }


        model.addAttribute("nomesCidades",nomes);
        model.addAttribute("ocorrenciasCidade", nrocorrencias);

    }

    public void tipoOrigem(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorTipoOrigem();


        String[] nomes = new String[lista.size()];
        BigInteger[] nrocorrencias = new BigInteger[lista.size()];
        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];
            nrocorrencias[i] = (BigInteger) ob[1];

            i++;
        }


        model.addAttribute("nomesTipoOringes",nomes);
        model.addAttribute("ocorrenciasOrigem", nrocorrencias);

    }


    public void entidade(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorEntidade();


        String[] nomes = new String[lista.size()];
        BigInteger[] nrocorrencias = new BigInteger[lista.size()];
        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];
            nrocorrencias[i] = (BigInteger) ob[1];

            i++;
        }


        model.addAttribute("nomesEntidade",nomes);
        model.addAttribute("ocorrenciasEntidade", nrocorrencias);

    }


    public void definicao(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorDefinicao();


        String[] nomes = new String[lista.size()];
        BigInteger[] nrocorrencias = new BigInteger[lista.size()];
        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];
            nrocorrencias[i] = (BigInteger) ob[1];

            i++;
        }


        model.addAttribute("nomesDefinicao",nomes);
        model.addAttribute("ocorrenciasDefinicao", nrocorrencias);

    }

    public void mes(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorMes();



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias1 = new BigInteger[lista.size()];
        BigInteger[] nrocorrencias2 = new BigInteger[lista.size()];
        BigInteger[] nrocorrencias3 = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias1[i] = (BigInteger) ob[1];
            nrocorrencias2[i] = (BigInteger) ob[2];
            nrocorrencias3[i] = (BigInteger) ob[3];


            i++;
        }


        model.addAttribute("nomesMes",nomes);
        model.addAttribute("numeroocorencia1", nrocorrencias1);
        model.addAttribute("numeroocorencia2", nrocorrencias2);
        model.addAttribute("numeroocorencia3", nrocorrencias3);

    }

    public void cidadeEstado(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCidadeEstado();



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias1 = new BigInteger[lista.size()];
        BigInteger[] nrocorrencias2 = new BigInteger[lista.size()];
        BigInteger[] nrocorrencias3 = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias1[i] = (BigInteger) ob[1];
            nrocorrencias2[i] = (BigInteger) ob[2];
            nrocorrencias3[i] = (BigInteger) ob[3];


            i++;
        }


        model.addAttribute("nomesCidades",nomes);
        model.addAttribute("numeroocorencia1", nrocorrencias1);
        model.addAttribute("numeroocorencia2", nrocorrencias2);
        model.addAttribute("numeroocorencia3", nrocorrencias3);

    }

    public void ProvinciaEstado(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProvinciaEstado(currentYear);



        String[] nomes = new String[lista.size()];

        BigDecimal[] nrocorrencias1 = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias2 = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias3 = new BigDecimal[lista.size()];


        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias1[i] = (BigDecimal) ob[1];
            nrocorrencias2[i] = (BigDecimal) ob[2];
            nrocorrencias3[i] = (BigDecimal) ob[3];



            i++;
        }


        model.addAttribute("nomesProvincias",nomes);
        model.addAttribute("numeroocorencia1", nrocorrencias1);
        model.addAttribute("numeroocorencia2", nrocorrencias2);
        model.addAttribute("numeroocorencia3", nrocorrencias3);


    }

    public void ProvinciaEstadoPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProvinciaEstadoPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);



        String[] nomes = new String[lista.size()];

        BigDecimal[] nrocorrencias1 = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias2 = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrencias3 = new BigDecimal[lista.size()];


        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias1[i] = (BigDecimal) ob[1];
            nrocorrencias2[i] = (BigDecimal) ob[2];
            nrocorrencias3[i] = (BigDecimal) ob[3];



            i++;
        }


        model.addAttribute("nomesProvincias",nomes);
        model.addAttribute("numeroocorencia1", nrocorrencias1);
        model.addAttribute("numeroocorencia2", nrocorrencias2);
        model.addAttribute("numeroocorencia3", nrocorrencias3);


    }

    public void canaDeEntrada(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalDeEntrada(currentYear);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomesCanais",nomes);
        model.addAttribute("numeroocorencia", nrocorrencias);

    }

    public void canaDeEntradaPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalDeEntradaPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomesCanais",nomes);
        model.addAttribute("numeroocorencia", nrocorrencias);

    }

    public void sexo(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorSexo(currentYear);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomeSexo",nomes);
        model.addAttribute("numeroocorenciaSexo", nrocorrencias);

    }

    public void sexoPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorPeSexoPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomeSexo",nomes);
        model.addAttribute("numeroocorenciaSexo", nrocorrencias);

    }


    public void tipoPreocupacao(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoTipoDePreocupacao(currentYear);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomeTipoPreocupacao",nomes);
        model.addAttribute("numeroocorenciaTipoPreocupacao", nrocorrencias);

    }

    public void tipoPreocupacaoPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoTipoDePreocupacaoPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomeTipoPreocupacao",nomes);
        model.addAttribute("numeroocorenciaTipoPreocupacao", nrocorrencias);

    }

    public void teste(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalDeEntrada(currentYear);

        String vetor = "";



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        Map<String, BigInteger> graphData = new TreeMap<>();


        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];

            graphData.put(nomes[i], nrocorrencias[i]);

            System.out.println(graphData.put(nomes[i], nrocorrencias[i]));
            i++;
        }

        model.addAttribute("vetor",graphData);
        //model.addAttribute("numeroocorencia", nrocorrencias);

    }


    public void categoria(Model model){

        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCategoria(currentYear);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomesCategoria",nomes);
        model.addAttribute("numeroocorenciaCategoria", nrocorrencias);

    }

    public void categoriaPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){

        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCategoriaPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }

        model.addAttribute("nomesCategoria",nomes);
        model.addAttribute("numeroocorenciaCategoria", nrocorrencias);

    }


    public void tipoOcorrencia(Model model){

        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorTipoDeOcorrencia();



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }


        model.addAttribute("nomesTipoOcorrencias",nomes);
        model.addAttribute("numeroocorenciaTipoOcorrencias", nrocorrencias);

    }

    public void regiao(Model model){

        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorRegiao(currentYear);



        String[] nomes = new String[lista.size()];

        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;
        for (Object[] ob : lista){

            nomes[i] = (String) ob[0];

            nrocorrencias[i] = (BigInteger) ob[1];


            i++;
        }




        model.addAttribute("nomesRegiao",nomes);
        model.addAttribute("numeroocorenciaRegiao", nrocorrencias);

    }


    public void busqueTnTI(Model model) {
        List<Object[]> lista = ocorrenciaRepository.busqueTnTI(currentYear);

        String[] nomes = new String[lista.size()];

        BigDecimal[] nrocorrenciasT = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrenciasNT = new BigDecimal[lista.size()];
        BigDecimal[] nrocorrenciasI = new BigDecimal[lista.size()];

        int i = 0;

        for (Object[] ob : lista) {

            nomes[i] = (String) ob[0];

            nrocorrenciasT[i] = (BigDecimal) ob[1];
            nrocorrenciasNT[i] = (BigDecimal) ob[2];
            nrocorrenciasI[i] = (BigDecimal) ob[3];

            i++;

        }

        model.addAttribute("nomesMeses",nomes);
        model.addAttribute("nrocorrenciasMesT", nrocorrenciasT);
        model.addAttribute("nrocorrenciasMesNT", nrocorrenciasNT);
        model.addAttribute("nrocorrenciasMesI", nrocorrenciasI);

    }

        public void busqueTnTIPesquisa(Model model,Date datainicial, Date datafinal,String tipoOcorrencia,String estado,String projecto,String provincia,String categoria){

            List<Object[]> lista = ocorrenciaRepository.busqueTnTIPesquisa(datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);

            String[] nomes = new String[lista.size()];

            BigDecimal[] nrocorrenciasT = new BigDecimal[lista.size()];
            BigDecimal[] nrocorrenciasNT = new BigDecimal[lista.size()];
            BigDecimal[] nrocorrenciasI = new BigDecimal[lista.size()];

            int i=0;

            for (Object[] ob : lista){

                nomes[i] = (String) ob[0];

                nrocorrenciasT[i] = (BigDecimal) ob[1];
                nrocorrenciasNT[i] = (BigDecimal) ob[2];
                nrocorrenciasI[i] = (BigDecimal) ob[3];

                i++;

            }

        model.addAttribute("nomesMeses",nomes);
        model.addAttribute("nrocorrenciasMesT", nrocorrenciasT);
        model.addAttribute("nrocorrenciasMesNT", nrocorrenciasNT);
        model.addAttribute("nrocorrenciasMesI", nrocorrenciasI);


    }

    public void busqueProjectoLinha(Model model){
        List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProjectoLinha(currentYear);

        String[] nomeProjecto = new String[lista.size()];
        String[] periodo = new String[lista.size()];
        BigInteger[] nrocorrencias = new BigInteger[lista.size()];

        int i=0;

        for (Object[] ob : lista){

            nomeProjecto[i] = (String) ob[0];
            periodo[i] = (String) ob[1];
            nrocorrencias[i] = (BigInteger) ob[2];


            System.out.println("PPPPPRROOOOJJJECCTOOO: "+nomeProjecto[i]);
            System.out.println("PPPPPEEERRIIIOOOOODDDOOOOO: "+periodo[i]);
            System.out.println("NNRR OCORRENCIAS : "+nrocorrencias[i]);

            i++;



        }

        model.addAttribute("nomeProjecto",nomeProjecto);
        model.addAttribute("periodo", periodo);
        model.addAttribute("nrocorrencias", nrocorrencias);


    }

    @GetMapping("/relatorio/ocorrencia")
    public String relatorio(Model model){
        model.addAttribute("ocorrencia", ocorrenciaRepository.ocorrenciasCorrentes(currentYear));
        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("projectos", projectoService.buscarTodos());
        model.addAttribute("tipoOcorrencias", tpService.buscarTodos());
        model.addAttribute("categorias", catService.buscarTodos());

        ProvinciaEstado(model);
        cidade(model);
        canaDeEntrada(model);
        categoria(model);
        projecto(model);
        tipoOcorrencia(model);
        regiao(model);
        busqueTnTI(model);
        teste(model);
        sexo(model);
        tipoPreocupacao(model);

        return "relatorio/ocorrencia";
    }

    @PostMapping("/ocorrencia/filtrar")
    public String filter1(@RequestParam("projecto") String projecto,
                          @RequestParam("provincia") String provincia,
                          @RequestParam("categoria") String categoria,
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
        model.addAttribute("categorias", catService.buscarTodos());

        service.addParams("categoria", categoria);
        service.addParams("projecto", projecto);
        service.addParams("data1", datainicial);
        service.addParams("data2", datafinal);
        service.addParams("provincia", provincia);
        service.addParams("tipoOcorrencia", tipoOcorrencia);
        service.addParams("estado", estado);

        model.addAttribute("ocorrencia", ocorrenciaRepository.totalDeOcorrenciasPorDataseProjectoRelatorio(datainicial,datafinal,projecto,provincia,estado,tipoOcorrencia, categoria));
        
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		String stringDatIncial= DateFor.format(datainicial);
		String stringDatFinal= DateFor.format(datafinal);


        //ProvinciaEstadoPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
        //busqueTnTIPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
        //canaDeEntradaPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
        //categoriaPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
        //sexoPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
        //tipoPreocupacaoPesquisa(model,datainicial,datafinal,tipoOcorrencia,estado,projecto,provincia,categoria);
		
		String projecto1="";
		String categoria1="";
		String provincia1="";
		String tipoOcorrencia1="";
		String estado1="";
		
		if(projecto!="") {
			projecto1=" - Porjecto: "+projecto;
		}
		
		if(provincia!="") {
			provincia1=" - Província: "+provincia;
		}
		if(categoria!="") {
			categoria1=" - Categoria: "+categoria;
		}
		
		if(tipoOcorrencia!="") {
			tipoOcorrencia1=" - Tipo de Ocorrência: "+tipoOcorrencia;
		}
		
		if(estado!="") {
			
			if(estado.equals("T")) {
				estado1=" - Nível de Resolução: Resolvido(s)";
			}
			
			if(estado.equals("R")) {
				estado1=" - Nível de Resolução: Em Resolução";
			}
			
			if(estado.equals("A")) {
				estado1=" - Nível de Resolução: Em Acopanhamento";
			}
			
		}
        
        model.addAttribute("dados", "De "+ stringDatIncial+" a "+stringDatFinal + " "+ projecto1+ " "+ provincia1+ " "+ categoria1+ " "+ tipoOcorrencia1+ " "+ estado1);


        return "relatorio/ocorrencia";

    }

}
