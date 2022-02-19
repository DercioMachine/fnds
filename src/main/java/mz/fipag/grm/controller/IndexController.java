package mz.fipag.grm.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	    
		
		 LocalDate currentdate = LocalDate.now();
		 int currentYear = currentdate.getYear();
	    
	   int ano=Calendar.getInstance().get(Calendar.YEAR);

		@Autowired
		private JavaMailSender javaMailSender;

		public static final String ACCOUNT_SID = "AC210a20618423ed1ec9fabcd1a047dc9c";
		public static final String AUTH_TOKEN = "627b5ac718dce7c7a69a95537fcc9f29";


    @GetMapping("/")
    public String index(){

		//sendEmail();
		//sendSMS();

        return "publico/principal";
    }

	void sendEmail() {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("jacintomachava@gmail.com");

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}
	void sendSMS() throws AuthenticationException {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(
				new com.twilio.type.PhoneNumber("9107084699"), 		// To number
				new com.twilio.type.PhoneNumber("9107084699"),		// From number
				"Notificacao de Sistema de Ocorrencias da FIPAG") // SMS body
				.create();

		System.out.println(message.getSid());

	}
    
    
    /*****************************************GRAFICOS DE OCORRENCIAS****************************************/



	public void projecto(Model model){
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProjecto();


		String[] legendaestado = new String[lista.size()];
		BigInteger[] nrocorrencias = new BigInteger[lista.size()];
		int i=0;
		for (Object[] ob : lista){

			legendaestado[i] = (String) ob[0];
			nrocorrencias[i] = (BigInteger) ob[1];

			i++;
		}


		model.addAttribute("nomesProjecto",legendaestado);
		model.addAttribute("numeroocorenciaProjecto", nrocorrencias);

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
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProvinciaEstado();
		


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


		model.addAttribute("nomesProvincias",nomes);
		model.addAttribute("numeroocorencia1", nrocorrencias1);
		model.addAttribute("numeroocorencia2", nrocorrencias2);
		model.addAttribute("numeroocorencia3", nrocorrencias3);

	}
	
	public void canaDeEntrada(Model model){
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalDeEntrada();
		


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
	
	public void categoria(Model model){

	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCategoria();
		


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

			List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorRegiao();
				


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
	
	
	public void busqueTnTI(Model model){
		List<Object[]> lista = ocorrenciaRepository.busqueTnTI();
		


		String[] nomes = new String[lista.size()];
		
		
		
		BigInteger[] nrocorrenciasT = new BigInteger[lista.size()];
		BigInteger[] nrocorrenciasNT = new BigInteger[lista.size()];
		BigInteger[] nrocorrenciasI = new BigInteger[lista.size()];
		
		int i=0;
		
		for (Object[] ob : lista){
			
		nomes[i] = (String) ob[0];
		
		nrocorrenciasT[i] = (BigInteger) ob[1];
		nrocorrenciasNT[i] = (BigInteger) ob[2];
		nrocorrenciasI[i] = (BigInteger) ob[3];
		
			i++;
		}
		
		System.out.println("NOmes dos meses = "+nomes);
		System.out.println("Terminados = "+nrocorrenciasT);
		System.out.println("Nao terminados = "+nrocorrenciasNT);
		System.out.println("Imporcidentes = "+nrocorrenciasI);
		
							//	model.addAttribute("nomesRegiao",nomes);
						//	model.addAttribute("numeroocorenciaRegiao", nrocorrencias);
	}

	
	
	
	
	

	
	@GetMapping("/estatistica")
	public String estatistica(Model model){
		
		model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias(currentYear));
		model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidar(currentYear));
		
		
		model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentes(currentYear));
		model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadas(currentYear));
		model.addAttribute("totalDeReclamacoesEmResolucao", ocorrenciaRepository.totalDeReclamacoesEmResolucao(currentYear));
		model.addAttribute("totalDeReclamacoesNaoProcedentes", ocorrenciaRepository.totalDeReclamacoesNaoProcedentes(currentYear));
		
		
		mes(model);
		//cidadeEstado(model);
		ProvinciaEstado(model);
		cidade(model);
		canaDeEntrada(model);
		categoria(model);
		projecto(model);
		tipoOcorrencia(model);
		regiao(model);
		busqueTnTI(model);
		
		
		return "publico/estastica";
	}
	
	
	
	@GetMapping("/relatorio")
	public String relatorio(Model model){
		
		model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias(currentYear));
		model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidar(currentYear));
		
		
		model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentes(currentYear));
		model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadas(currentYear));
		model.addAttribute("totalDeReclamacoesEmResolucao", ocorrenciaRepository.totalDeReclamacoesEmResolucao(currentYear));
		model.addAttribute("totalDeReclamacoesNaoProcedentes", ocorrenciaRepository.totalDeReclamacoesNaoProcedentes(currentYear));
		
		
		mes(model);
		//cidadeEstado(model);
		ProvinciaEstado(model);
		cidade(model);
		canaDeEntrada(model);
		categoria(model);
		projecto(model);
		
		return "publico/relatorio";
	}
	
    
    @GetMapping("/estatistic")
    public ModelAndView estatistica(){
    	
    	ModelAndView vm = new ModelAndView("publico/estastica");
		
		vm.addObject("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias(currentYear));
		vm.addObject("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentes(currentYear));
		vm.addObject("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentes(currentYear));
		vm.addObject("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidar(currentYear));
		
		vm.addObject("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentes(currentYear));
		vm.addObject("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadas(currentYear));
		vm.addObject("totalDeReclamacoesEmResolucao", ocorrenciaRepository.totalDeReclamacoesEmResolucao(currentYear));
		vm.addObject("totalDeReclamacoesNaoProcedentes", ocorrenciaRepository.totalDeReclamacoesNaoProcedentes(currentYear));
		
		
		
		return vm;

    }


	@GetMapping("/dashboard")
	public String estatisticateste(Model model){
		
		model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias(currentYear));
		model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidar(currentYear));
		
		
		model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentes(currentYear));
		model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadas(currentYear));
		model.addAttribute("totalDeReclamacoesEmResolucao", ocorrenciaRepository.totalDeReclamacoesEmResolucao(currentYear));
		model.addAttribute("totalDeReclamacoesNaoProcedentes", ocorrenciaRepository.totalDeReclamacoesNaoProcedentes(currentYear));
		
		mes(model);
		//cidadeEstado(model);
		ProvinciaEstado(model);
		cidade(model);
		canaDeEntrada(model);
		categoria(model);
		projecto(model);
		tipoOcorrencia(model);
		regiao(model);
		
//		projecto(model);
//		categoria(model);
//		cidade(model);
//		tipoOrigem(model);
//		entidade(model);
//		definicao(model);
//		
		return "reclamacoes/home";
	}
	

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
	public String preCadastrarOcorrencia(Ocorrencia ocorrencia, ModelMap model, Cidade cidade, @RequestParam("descricaoAnx") String descricaoAnx, @RequestParam("files") MultipartFile[] files, BindingResult result, RedirectAttributes attr) {

    	
    	if (result.hasErrors()) {
			System.out.println("Entra no if");
    		return "apresentar/preocupacao";
			
		}else {
			System.out.println("Nao Entra no if");
			
		}
    	
    	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
    	int ano = Calendar.getInstance().get(Calendar.YEAR);
    	
    	String anoo = String.valueOf(ano);
    	String anooo= anoo.substring(2, 4);
    	
    	
    	
    		ocorrencia.setGrmStamp(cidade.getProvincia().getId()+""+codigo+""+anooo);
    		ocorrencia.setEstado("Temporario");
    	
    		ocorrencia.setTemporario(true);
    		
    		System.out.println("Antes de salvar");
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
    	
    	
    	return "redirect:/";
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
