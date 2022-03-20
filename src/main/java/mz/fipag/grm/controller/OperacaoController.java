package mz.fipag.grm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mz.fipag.grm.domain.Assunto;
import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.domain.Operacao;
import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.repository.AssuntoRepository;
import mz.fipag.grm.repository.DistritoRepository;
import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.repository.PostoAdminitrativoRepository;
import mz.fipag.grm.repository.ProcessoRepository;
import mz.fipag.grm.repository.ResolucaoRepository;
import mz.fipag.grm.repository.ResponsabilidadeRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.DistritoService;
import mz.fipag.grm.service.DocStorageService;
import mz.fipag.grm.service.EmpreiterioService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.OperacaoService;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.service.TipoAlertaService;
import mz.fipag.grm.service.TipoOcorrenciaService;
import mz.fipag.grm.util.PaginacaoUtil;


@Controller
public class OperacaoController {
	
	@Autowired
	private OperacaoService operacaoService;

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
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private ProcessoRepository processoRepository ;
    
    @Autowired
    private CategoriaService categoriaService; 
    
    @Autowired
    private AssuntoRepository assuntoRepository;
    
    private long responsavel;

    @GetMapping("/listar/operacao")
    public String listarOperacao(ModelMap model, @RequestParam("page") Optional<Integer> page){

    	int paginaActual = page.orElse(1);
		PaginacaoUtil<Operacao> pageOperacao = operacaoService.buscaPorPagina(paginaActual);

		model.addAttribute("pageOperacao", pageOperacao);

        return "operacao/listar";
    }
    
    

    @GetMapping("/listar/operacaoOservador")
    public String operacaoOservador(ModelMap model, @RequestParam("page") Optional<Integer> page){

    	int paginaActual = page.orElse(1);
		PaginacaoUtil<Operacao> pageOperacao = operacaoService.buscaPorPagina(paginaActual);

		model.addAttribute("pageOperacao", pageOperacao);

        return "operacao/listar";
    }

    
    @GetMapping("/registar/operacao")
    public String novaOcorrencia(ModelMap model){

        model.addAttribute("operacao",new Operacao());

        return "operacao/registar";
    }


   /* @PostMapping("/operacao/cadastrar")
	public String salvarOperacao(Operacao operacao, Provincia provincia, Cidade cidade) {

    	
    	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
    	int ano = Calendar.getInstance().get(Calendar.YEAR);
    	
    	String anoo = String.valueOf(ano);
    	String anooo= anoo.substring(2, 4);
    	operacao.setGrmStamp(operacao.getCidade().getProvincia().getCodigo()+""+codigo+""+anooo);
    	operacaoService.salvar(operacao);

    	return "redirect:/listar/operacao";
	}*/
    

    @PostMapping("/operacoes/editar") 
	  public String editarOperacao(Operacao operacao) {
    	
    	operacaoService.editar(operacao);
		  
		  return "redirect:/listar/operacao"; 
	  }
    
    @GetMapping("/operacoes/editar/{id}") 
	  public String vistaEditarOperacao(@PathVariable("id") Long id, ModelMap model) {
	  
    	model.addAttribute("operacao", operacaoService.buscarPorId(id));
	  
	  return "operacao/editar"; 
	  }
    
    
    
    @GetMapping("/operacoes/detalhesOperacao/{id}")
    public String listarDetalhes(@PathVariable("id") Long id, ModelMap model){

        model.addAttribute("operacao", operacaoService.buscarTodos());

        return "operacao/detalhar";
    }
    
    
    
    


    @PostMapping("/operacoes/listar") 
	  public String listarOperacao(Operacao operacao) {
  	
  	operacaoService.editar(operacao);
		  
		  return "redirect:/listar/operacao"; 
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

	@ModelAttribute("projectos")
	public List<Projecto> listaDeDeProjectos(){
		return projectoService.buscarTodos();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> listaDeCategorias(){
		return categoriaService.buscarTodos();
	}
	
	@ModelAttribute("assuntos")
	public List<Assunto> listaDeAssuntos(){
		return (List<Assunto>) assuntoRepository.findAll();
	}

}
