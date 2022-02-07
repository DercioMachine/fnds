package mz.fipag.grm.controller;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import mz.fipag.grm.repository.OcorrenciaRepository;
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

@Controller
public class IndexController {
	
	
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
	    private OcorrenciaRepository ocorrenciaRepository;

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
	    
	    
	

    @GetMapping("/")
    public String index(){

        return "publico/principal";
    }
    
    
    @GetMapping("/estatistica")
    public ModelAndView estatistica(){
    	
    	
    	ModelAndView vm = new ModelAndView("publico/estastica");
		
		vm.addObject("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias());
		vm.addObject("totalDeSugestoes", ocorrenciaRepository.totalDeSugestoes());
		vm.addObject("totalDeReclamacoes", ocorrenciaRepository.totalDeReclamacoes());
		
		//vm.addObject("totalDeOcorrenciasPorMes", ocorrenciaRepository.totalDeOcorrenciasPorMes());
		
		
		return vm;

    }

	/*
	 * @GetMapping("/login") public String homeIndex(){
	 * 
	 * return "login"; }
	 */

	@GetMapping("/dashboard")
	public String estatisticateste(Model model){

		String label[] = {"a","b","c","d","e","f","g"};

		int point[] = {5,3,7,1,8,3,4};

		model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias());
		model.addAttribute("totalDeSugestoes", ocorrenciaRepository.totalDeSugestoes());
		model.addAttribute("totalDeReclamacoes", ocorrenciaRepository.totalDeReclamacoes());

		model.addAttribute("label",label);
		model.addAttribute("point",point);

		return "reclamacoes/home";
	}
    /*
    @GetMapping("/dashboard")
    public ModelAndView dashboard(Model model) {
		
		ModelAndView vm = new ModelAndView("reclamacoes/home");

		String label[] = {"a","b","c","d","e","f","g"};

		int point[] = {5,3,7,1,8,3,4,};
		
		vm.addObject("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias());
		vm.addObject("totalDeSugestoes", ocorrenciaRepository.totalDeSugestoes());
		vm.addObject("totalDeReclamacoes", ocorrenciaRepository.totalDeReclamacoes());

		model.addAttribute("label",label);

		model.addAttribute("point",point);
		
		//vm.addObject("totalDeOcorrenciasPorMes", ocorrenciaRepository.totalDeOcorrenciasPorMes());
		
		
		
		return vm;
		
	}  */
    

    @GetMapping("/home")
    public String home(ModelMap model){
    	
    	 model.addAttribute("ocorrencia",new Ocorrencia());

        return "reclamacoes/home";
    }
    
   
    @GetMapping("/buscar/preocupacao")
    public String bucarPreocupacao(){

        return "publico/acompanharQueixa";
    }
    
    @GetMapping("/login")
    public String login(){

        return "publico/login";
    }
    
    @GetMapping("/apresentar/preocupacao")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("ocorrencia",new Ocorrencia());

        return "publico/apresentarPreocupacao";
    }

	@GetMapping("/apresentar/estatistica")
	public String apresentarEstartistica(ModelMap model){

		model.addAttribute("ocorrencia",new Ocorrencia());

		return "publico/apresentarEstatistica";
	}
    
    
    @PostMapping("/preCadastrar")
	public String preCadastrarOcorrencia(Ocorrencia ocorrencia, ModelMap model, Provincia provincia, @RequestParam("descricao") String descricaoAnx, @RequestParam("files") MultipartFile[] files, BindingResult result, RedirectAttributes attr) {

    	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
    	int ano = Calendar.getInstance().get(Calendar.YEAR);
    	
    	String anoo = String.valueOf(ano);
    	String anooo= anoo.substring(2, 4);
    	
    	
    	
    		ocorrencia.setGrmStamp(provincia.getCodigo()+""+codigo+""+anooo);
    		ocorrencia.setEstado("Temporario");
    	
    		ocorrencia.setTemporario(true);
    		
    		
    		if (result.hasErrors()) {
    			return "apresentar/preocupacao";
    		}
    		
    		
    		ocorrenciaService.salvar(ocorrencia);
    		
    		if(files!=null) {
    			for(MultipartFile file: files) {
    				
    				if(!file.getOriginalFilename().isEmpty()) {
    					
    					docStorageService.saveFile(file, ocorrencia, descricaoAnx);
    				}
    	        }
    		}
    		
    	// model.addAttribute("ocorrenciaa", ocorrencia.getGrmStamp());

    	attr.addFlashAttribute("success", "Preocupação submetida com sucesso.");
    	
    	attr.addFlashAttribute("ocorrenciaa", ocorrenciaService.buscarPorId(ocorrencia.getId()));
    	
    	
    	return "redirect:/apresentar/preocupacao";
	}
    
    
    
    
    @PostMapping("/autenticacao")
	public String autenticar(@RequestParam("user") String user, @RequestParam("senha") String senha, RedirectAttributes attr) {

		if(user.equals("fipag@fipag.co.mz") && senha.equals("123")) {
			
			return "redirect:/dashboard";
		}else {
			attr.addFlashAttribute("error", "Utilizador ou Senha invalida.");
			return "redirect:/login";
		}
		
	}
    
    
    
    @PostMapping("/pesquisar/ocorrencia")
    public String pesquisarOcorrencia(Ocorrencia ocorrencia, @RequestParam long codigo, ModelMap model ) {

    	ocorrencia = ocorrenciaRepository.findAllByCodigo(codigo);
    	
    	
    	
    	model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(ocorrencia.getId()));
        model.addAttribute("anexos", docsRepository.findAllByIdResolucao(ocorrencia.getId()));
        model.addAttribute("resolucoes", resolucaoRepository.findByOcorrencia(ocorrencia.getId()));
        model.addAttribute("resolver", new Resolucao());
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "redirect:/ver/detalhe/"+ocorrencia.getId();
    }
    
    
    @GetMapping("/ver/detalhe/{id}")
    public String resolverOcorrencia(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
        model.addAttribute("anexos", docsRepository.findAllByIdResolucao(id));
        model.addAttribute("resolucoes", resolucaoRepository.findByOcorrencia(id));
        model.addAttribute("resolver", new Resolucao());
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "publico/detalhe";
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
