package mz.fipag.grm.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.domain.Resolucao;
import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.repository.DistritoRepository;
import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.repository.PostoAdminitrativoRepository;
import mz.fipag.grm.repository.ResolucaoRepository;
import mz.fipag.grm.repository.ResponsabilidadeRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.CidadeService;
import mz.fipag.grm.service.DistritoService;
import mz.fipag.grm.service.DocStorageService;
import mz.fipag.grm.service.EmpreiterioService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.service.TipoAlertaService;
import mz.fipag.grm.service.TipoOcorrenciaService;
import mz.fipag.grm.util.PaginacaoUtil;


@Controller
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;
    
    @Autowired
    private ProvinciaService provinciaService;
    
    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private DocsRepository docsRepository;

    @Autowired
    private PostoAdminitrativoRepository postoAdminitrativoRepository;

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private TipoAlertaService tipoAlertaService;

    @Autowired
    private ResponsabilidadeRepository responsabilidadeRepository;

    @Autowired
    private PostoAdministrativoService postoAdminService;

    @Autowired
    private ResolucaoRepository resolucaoRepository;

    @Autowired
    private TipoOcorrenciaService tipoDeOcorrenciasService;

    @Autowired
    private DocStorageService docStorageService;
    
    @Autowired
    private ProjectoService projectoService;
    
    @Autowired
    private EmpreiterioService empreiteiroService;
    
    @Autowired
    private CidadeService cidadeService;
    
    @Autowired
    private CategoriaService categoriaService; 
    
    private long responsavel;

    @GetMapping("/listar/ocorrencia")
    public String listarOcorrencia(ModelMap model, @RequestParam("page") Optional<Integer> page){

    	int paginaActual = page.orElse(1);
		PaginacaoUtil<Ocorrencia> pageOcorrencia = ocorrenciaService.buscaPorPagina(paginaActual);

		model.addAttribute("pageOcorrencia", pageOcorrencia);
        //model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/listarOcorrencia";
    }
    
    
    @GetMapping("/listar/teste")
    public String litarTeste(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/teste";
    }
    
    @GetMapping("/listar/ocorrencia2")
    public String listarOcorrencia2(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/listarOcorrencia2";
    }
    
    @GetMapping("/registar/ocorrencia")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "ocorrencia/registarOcorrenciaCompleta";
    }

    @ResponseBody
    @GetMapping("/distrito/{id}")
    public String listarDistrito(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(distritoRepository.findAllById(id));
    }


    @ResponseBody
    @GetMapping("/posto/{id}")
    public String listarPosto(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(postoAdminitrativoRepository.findAllById(id));
    }


    @PostMapping("/ocorrencias/cadastrar")
	public String salvarOcorrencia(Ocorrencia ocorrencia, Provincia provincia, @RequestParam("descricaoAnx") String descricaoNexo, @RequestParam("files") MultipartFile[] files) {

    	
    	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
    	int ano = Calendar.getInstance().get(Calendar.YEAR);
    	
    	String anoo = String.valueOf(ano);
    	String anooo= anoo.substring(2, 4);
    	
    	
    	
    		ocorrencia.setGrmStamp(provincia.getCodigo()+""+codigo+""+anooo);
    		ocorrencia.setRegistado(true);
    		ocorrencia.setEstado("Registado");
    		ocorrenciaService.salvar(ocorrencia);

    	for(MultipartFile file: files) {
    		if(!file.getOriginalFilename().isEmpty()) {
				
				docStorageService.saveFile(file, ocorrencia, descricaoNexo);
			}
        }

    	return "redirect:/listar/ocorrencia";
	}
    
    @PostMapping("/ocorrencias/listar")
   	public String verOcorrencias(Ocorrencia ocorrencia) {

   		/*
   		 * if (result.hasErrors()) { return "cargo/cadastro"; }
   		 */
       //	ocorrenciaService.salvar(ocorrencia);
       	return "redirect:/listar/ocorrencia";
   	}

    @PostMapping("/ocorrencias/editar") 
	  public String editarCategoria(Ocorrencia ocorrencia) {
    	
	  
    		ocorrencia.setRegistado(true);
    		ocorrencia.setEstado("Registado");
    		ocorrenciaService.editar(ocorrencia);
		  
		  return "redirect:/listar/ocorrencia"; 
	  }
    
    @GetMapping("/ocorrencias/editar/{id}") 
	  public String vistaEditarOcorrencia(@PathVariable("id") Long id, ModelMap model) {
	  
    	model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
	  
	  return "ocorrencia/editarOcorrencia"; 
	  }

    @GetMapping("/resolver/ocorrencia/{id}")
    public String resolverOcorrencia(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
        model.addAttribute("anexos", docsRepository.findAllByIdResolucao(id));
        model.addAttribute("resolucoes", resolucaoRepository.findByOcorrencia(id));
        model.addAttribute("resolver", new Resolucao());
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "ocorrencia/resolucao";
    }

    @PostMapping("/cadastrar/acompanhamento")
    public String cadastrarAcompanhamento(Ocorrencia ocorrencia,
                                     Resolucao resolucao,
                                     @RequestParam long ocorrencia2,
                                     @RequestParam boolean report
    ) {

        ocorrencia = ocorrenciaService.buscarPorId(ocorrencia2);


        if(report == true){

            resolucao.setOcorrencia(ocorrencia);
            resolucaoRepository.save(resolucao);
            ocorrencia.setResolucao("T");
            ocorrenciaService.salvar(ocorrencia);


        }else{

            ocorrencia.setResolucao("A");
            resolucao.setTipo("A");
            resolucao.setOcorrencia(ocorrencia);
            resolucaoRepository.save(resolucao);
            ocorrenciaService.salvar(ocorrencia);


        }

        return "redirect:/resolver/ocorrencia/"+ocorrencia2;
    }

    @PostMapping("/cadastrar/resolucao")
    public String cadastrarResolucao(Ocorrencia ocorrencia,
                                     Resolucao resolucao,
                                     @RequestParam long ocorrencia2,
                                     @RequestParam boolean report
                                     ) {

      ocorrencia = ocorrenciaService.buscarPorId(ocorrencia2);

        if(report == true){

            
            resolucao.setOcorrencia(ocorrencia);
            resolucaoRepository.save(resolucao);
            ocorrencia.setResolucao("T");
            ocorrenciaService.salvar(ocorrencia);

        }else{
        	   if(resolucao.getResponsabilidade().getId() == 4){
                   ocorrencia.setResolucao("A");
                   resolucao.setTipo("A");
               }else{
                   ocorrencia.setResolucao("R");
                   resolucao.setTipo("R");
               }
               resolucao.setOcorrencia(ocorrencia);
               resolucaoRepository.save(resolucao);
               ocorrenciaService.salvar(ocorrencia);
           
        }

        return "redirect:/resolver/ocorrencia/"+ocorrencia2;
    }

    @PostMapping("/anexos/resolucao")
    public String anexarResolucao(Ocorrencia ocorrencia,
                                  @RequestParam long ocorrencia2,
                                  @RequestParam String descricao,
                                  @RequestParam String fase,
                                  @RequestParam("files") MultipartFile[] files) {

        ocorrencia = ocorrenciaService.buscarPorId(ocorrencia2);

        for(MultipartFile file: files) {
            docStorageService.saveFileResolucao(file, ocorrencia,descricao,fase);
        }

        return "redirect:/resolver/ocorrencia/"+ocorrencia2;
    }

    
    @PostMapping("/ocorrencias/fase2")
    public String editarOcorrenciaFase2Vista(Ocorrencia ocorrencia) {

        ocorrenciaService.editar(ocorrencia);
        
        return "redirect:/listar/ocorrencia";
    }
    
    @GetMapping("/ocorrencias/fase2/{id}")
    public String editarOcorrenciaFase2(@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
		
        return "ocorrencia/editarOcorrencia";
    }

    @GetMapping("/listar/detalhes")
    public String listarDetalhes(ModelMap model){

        model.addAttribute("ocorrencias", ocorrenciaService.buscarTodos());

        return "ocorrencia/detalharOcorrencia";
    }
    
    @PostMapping("/ocorrencias/detalhar")
    public String detalhesVista(Ocorrencia ocorrencia) {

        ocorrenciaService.editar(ocorrencia);
        
        return "redirect:/listar/detalhes";
    }
    
    @GetMapping("/ocorrencias/detalhar/{id}")
    public String detalhesVistaAccao (@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
    	 model.addAttribute("anexos", docsRepository.findAllById(id));

        return "ocorrencia/detalharOcorrencia";

    }
    
    /*
	 *  VALIDACAO
	 *
	 */
    
    

    @PostMapping("/ocorrencias/validar")
    public String validacaoVista(Ocorrencia ocorrencia,  RedirectAttributes redirAttrs,
    		@RequestParam String procedencia, @RequestParam("descricao") String descricaoAnexo, @RequestParam("files") MultipartFile[] files) {
    		
    		ocorrencia.setProcedencia(procedencia);
    		ocorrencia.setValidado(true);
        	ocorrencia.setEstado("Validado");
        	ocorrencia.setResolucao("V");
            ocorrenciaService.editar(ocorrencia);
            
            for(MultipartFile file: files) {
        		if(!file.getOriginalFilename().isEmpty()) {
    				
    				docStorageService.saveFileValidacao(file, ocorrencia, descricaoAnexo);
    			}
            }

            return "redirect:/resolver/ocorrencia/"+ocorrencia.getId();

    }

    @GetMapping("/ocorrencias/validar/{id}")
    public String validacaoAccao (@PathVariable("id") Long id, ModelMap model) {

    	 model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));

        return "ocorrencia/registarValidacao";
    }
    
    
    @GetMapping("/ocorrencias/excluir/{id}") 
    public String vistaExcluirCategoria(@PathVariable("id") Long id, ModelMap model) {
		  ocorrenciaService.excluir(id); 
		  return "redirect:/listar/ocorrencia"; 
	  }



    @ModelAttribute("provincias")
	public List<Provincia> listaDeDePronvicias() {
		return provinciaService.buscarTodos();
	}	
    
    @ModelAttribute("distritos")
	public List<Distrito> listaDeDistritos() {
		return distritoService.buscarTodos();
	}	
    
    @ModelAttribute("postos")
	public List<PostoAdministrativo> listaDePostos() {
		return postoAdminService.buscarTodos();
	}
    
    @ModelAttribute("tipoDeOcorrencias")
	public List<TipoOcorrencia> listaDetipoDeOcorrencias() {
		return tipoDeOcorrenciasService.buscarTodos();
	}
    
    @ModelAttribute("tipoAlertas")
    	public List<TipoAlerta> listaDetipoDeAlertas(){
    	return tipoAlertaService.buscarTodos();
   }
    
    @ModelAttribute("empreiteiros")
	public List<Empreiteiro> listaDeEmpreiteiros(){
		return empreiteiroService.buscarTodos();
}
    
    @ModelAttribute("cidades")
	public List<Cidade> listaDeCidades(){
	return cidadeService.buscarTodos();
}

	@ModelAttribute("projectos")
	public List<Projecto> listaDeDeProjectos(){
		return projectoService.buscarTodos();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> listaDeCategorias(){
		return categoriaService.buscarTodos();
	}


}
