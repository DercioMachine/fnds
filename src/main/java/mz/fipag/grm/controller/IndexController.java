package mz.fipag.grm.controller;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;

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
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.DistritoRepository;
import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.repository.PostoAdminitrativoRepository;
import mz.fipag.grm.repository.ResolucaoRepository;
import mz.fipag.grm.repository.ResponsabilidadeRepository;
import mz.fipag.grm.repository.UserRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.CidadeService;
import mz.fipag.grm.service.DistritoService;
import mz.fipag.grm.service.DocStorageService;
import mz.fipag.grm.service.EmailService;
import mz.fipag.grm.service.EmpreiterioService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.service.SMSService;
import mz.fipag.grm.service.TipoAlertaService;
import mz.fipag.grm.service.TipoOcorrenciaService;

@Controller
public class IndexController {
	
		@Autowired
		private SMSService smsService;
		
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

	    @Autowired
	    UserRepository userRepository;
	    
		@Autowired
		private EmailService emailService;

		@Autowired
		private PasswordEncoder passwordEncoder;
	    
		
		 LocalDate currentdate = LocalDate.now();
		 int currentYear = currentdate.getYear();
	    
	   int ano=Calendar.getInstance().get(Calendar.YEAR);

		@Autowired
		private JavaMailSender javaMailSender;

		public static final String ACCOUNT_SID = "AC4496682b72c01c4ca07f35400642a526";
		public static final String AUTH_TOKEN = "ae7fd2ea9996bffcbc84b3dc60ca3936";


    @GetMapping("/")
    public String index() throws MessagingException {

		//sendEmail();
		//sendSMS();

		//emailService.enviarEmail("descricao","Jacinto Machava","jacintomachava@gmail.com","covid19");

        return "publico/principal";
    }

	@GetMapping("/recuperar/senha")
	public String RecuperarSenha(){

		return "publico/recuperarSenha";
	}

	@PostMapping("/recuperar/password")
	public String recuperarPassword(User user, @RequestParam String telefone,@RequestParam String email, ModelMap model, RedirectAttributes attr ) throws MessagingException {

		user = userRepository.pesquisarPorTelefoneOuEmail(telefone,email);

		System.out.println("User "+user);

		if(user==null) {

			String telefone1="";
			String email1="";

			if(telefone!=null){
				telefone1 = "telefone: "+telefone;}

			if(email!=null){
				email1 = "email: "+email;}

			model.addAttribute("error", "Não existe nenhum utilizador com "+telefone1+" "+email1);

			return "publico/recuperarSenha";
		}
		int codigo = ThreadLocalRandom.current().nextInt(99999, 1000000);
		String novasenha = ""+codigo;

		user.setPassword(passwordEncoder.encode(novasenha));
		userRepository.save(user);

		String contacto = telefone.isEmpty() ? null : telefone;
		String emaill = email.isEmpty() ? null : email;


		if(contacto!=null){

			String mensagem = "A sua Senha Foi recuperada com sucesso, a sua nova senha é: "+novasenha;
			smsService.sendSMS("+258"+user.getTelefone(),mensagem);

		}
		if(emaill!=null){

			String descricao = "A sua Senha foi recuperada com sucesso, a sua nova senha é: "+novasenha;
			String nome = "A sua Senha foi recuperada com sucesso";
			String destino = user.getEmail();
			String assunto = "Recuperação da Senha";

			emailService.enviarEmail(descricao,nome,destino,assunto);

		}


		//attr.addFlashAttribute("success", "Recuperação efectuado com sucesso, verifique o teu telefone/email");

		model.addAttribute("success", "Recuperação efectuado com sucesso, verifique o teu telefone/email");

		return "publico/login";
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
				new com.twilio.type.PhoneNumber("+258844870386"), 		// To number
				new com.twilio.type.PhoneNumber("+258844870386"),		// From number
				"Notificacao de Sistema de Ocorrencias da FIPAG") // SMS body
				.create();

		System.out.println(message.getSid());

	}
    
    
    /*****************************************GRAFICOS DE OCORRENCIAS****************************************/



	public void projecto(Model model){
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProjecto();


		String[] legendaestado = new String[lista.size()];
		BigInteger[] nrocorrencias = new BigInteger[lista.size()];
		BigInteger[] nrocorrencias2 = new BigInteger[lista.size()];
		BigInteger[] nrocorrencias3 = new BigInteger[lista.size()];
		
		
		int i=0;
		for (Object[] ob : lista){

			legendaestado[i] = (String) ob[0];
			nrocorrencias[i] = (BigInteger) ob[1];
			nrocorrencias2[i] = (BigInteger) ob[2];
			nrocorrencias3[i] = (BigInteger) ob[3];
			

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
		
		model.addAttribute("nomesMeses",nomes);
		model.addAttribute("nrocorrenciasMesT", nrocorrenciasT);
		model.addAttribute("nrocorrenciasMesNT", nrocorrenciasNT);
		model.addAttribute("nrocorrenciasMesI", nrocorrenciasI);
		

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
		model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoes(currentYear));


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
	
	
	@GetMapping("/dashboard")
	public String dashboard(Model model){
		
		model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrencias(currentYear));
		model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidar(currentYear));
		
		
		model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentes(currentYear));
		model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadas(currentYear));
		model.addAttribute("totalDeReclamacoesEmResolucao", ocorrenciaRepository.totalDeReclamacoesEmResolucao(currentYear));
		model.addAttribute("totalDeReclamacoesNaoProcedentes", ocorrenciaRepository.totalDeReclamacoesNaoProcedentes(currentYear));
		model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoes(currentYear));


		ProvinciaEstado(model);
		cidade(model);
		canaDeEntrada(model);
		categoria(model);
		projecto(model);
		tipoOcorrencia(model);
		regiao(model);
		busqueTnTI(model);
		
		
		return "reclamacoes/home";
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
    
public void cidadeFiltro(Model model, Date datainicial, Date datafinal, String projecto){
    	
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCidadeFiltro1(datainicial, datafinal, projecto);


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
    
    
public void regiaoFiltro(Model model, Date datainicial, Date datafinal, String projecto){
    	
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorRegiaoFiltro1(datainicial, datafinal, projecto);


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


	public void canalEntradaFiltro(Model model, Date datainicial, Date datafinal, String projecto){
		
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalEntradaFiltro1(datainicial, datafinal, projecto);
	
	
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
	
	
public void mesTnTIFiltro(Model model, Date datainicial, Date datafinal, String projecto){
		
		List<Object[]> lista = ocorrenciaRepository.busqueTnTIFiltro(datainicial, datafinal, projecto);
	
	
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
				
				model.addAttribute("nomesMeses",nomes);
				model.addAttribute("nrocorrenciasMesT", nrocorrenciasT);
				model.addAttribute("nrocorrenciasMesNT", nrocorrenciasNT);
				model.addAttribute("nrocorrenciasMesI", nrocorrenciasI);
				

}


public void provinciaTnTIFiltro(Model model, Date datainicial, Date datafinal, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueProvinciaTnTPFiltro(datainicial, datafinal, projecto);


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
			
			model.addAttribute("nomesProvincias",nomes);
			model.addAttribute("numeroocorencia1", nrocorrenciasT);
			model.addAttribute("numeroocorencia2", nrocorrenciasNT);
			model.addAttribute("numeroocorencia3", nrocorrenciasI);
			

}

public void projectoTnTIFiltro(Model model, Date datainicial, Date datafinal, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueProjectoTnTPFiltro(datainicial, datafinal, projecto);


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
			
			model.addAttribute("nomesProjecto",nomes);
			model.addAttribute("numeroocorenciaProjecto", nrocorrenciasT);
			model.addAttribute("numeroocorenciaProjecto2", nrocorrenciasNT);
			model.addAttribute("numeroocorenciaProjecto3", nrocorrenciasI);
			

}




public void categoriaFiltro(Model model, Date datainicial, Date datafinal, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCategoriaFiltro1(datainicial, datafinal, projecto);


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



// CHAMA METODO INTERNO

@PostMapping("/filtrar1")
	public String filtrar1(@RequestParam("datainicial") Date datainicial,
							@RequestParam("datafinal") Date datafinal,
							@RequestParam("projecto") String projecto,
							Model model) {

		
		model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentesFiltro(datainicial, datafinal, projecto));
		model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentesFiltro(datainicial, datafinal, projecto));
		model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadasFiltro(datainicial, datafinal, projecto));
		model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoesFiltro(datainicial, datafinal, projecto));
		
		String projecto1="";
		
		if(projecto!="") {
			projecto1=" do Projecto "+projecto;
		}
		
		
		
		

			cidadeFiltro(model, datainicial, datafinal, projecto);
			regiaoFiltro(model, datainicial, datafinal, projecto);
			canalEntradaFiltro(model, datainicial, datafinal, projecto);
			categoriaFiltro(model, datainicial, datafinal, projecto);
			//mesTnTIFiltro(model, datainicial, datafinal, projecto);
			
			mesTnTIFiltro(model, datainicial, datafinal, projecto);
			provinciaTnTIFiltro(model, datainicial, datafinal, projecto);
			projectoTnTIFiltro(model, datainicial, datafinal, projecto);
			
			
			SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
			String stringDatIncial= DateFor.format(datainicial);
			String stringDatFinal= DateFor.format(datafinal);
			
			model.addAttribute("dados1", "De "+ stringDatIncial+" a "+stringDatFinal + " "+ projecto1);
			
			return "reclamacoes/home";
		
	}


// CHAMA EXTERNO

@PostMapping("/filter1")
public String filter1(@RequestParam("datainicial") Date datainicial,
						@RequestParam("datafinal") Date datafinal,
						@RequestParam("projecto") String projecto,
						Model model) {

	
	
	model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentesFiltro(datainicial, datafinal, projecto));
	model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentesFiltro(datainicial, datafinal, projecto));
	model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadasFiltro(datainicial, datafinal, projecto));
	model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoesFiltro(datainicial, datafinal, projecto));
	model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrenciasFiltro1(datainicial, datafinal, projecto));
	model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentesFiltro1(datainicial, datafinal, projecto));
	model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidarFiltro1(datainicial, datafinal, projecto));
	

		cidadeFiltro(model, datainicial, datafinal, projecto);
		regiaoFiltro(model, datainicial, datafinal, projecto);
		canalEntradaFiltro(model, datainicial, datafinal, projecto);
		categoriaFiltro(model, datainicial, datafinal, projecto);
		//mesTnTIFiltro(model, datainicial, datafinal, projecto);
		
		mesTnTIFiltro(model, datainicial, datafinal, projecto);
		provinciaTnTIFiltro(model, datainicial, datafinal, projecto);
		projectoTnTIFiltro(model, datainicial, datafinal, projecto);
		
		return "publico/estastica";
	
}



public void cidadeFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCidadeFiltro2(radioButton, codSelected, projecto, ano);


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


public void regiaoFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorRegiaoFiltro2(radioButton, codSelected, projecto, ano);


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


public void canalEntradaFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCanalEntradaFiltro2(radioButton, codSelected, projecto, ano);


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

public void canalCategoriaFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCategoriaFiltro2(radioButton, codSelected, projecto, ano);


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


public void mesTnTIFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorMesFiltro2(radioButton, codSelected, projecto, ano);


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
			
			model.addAttribute("nomesMeses",nomes);
			model.addAttribute("nrocorrenciasMesT", nrocorrenciasT);
			model.addAttribute("nrocorrenciasMesNT", nrocorrenciasNT);
			model.addAttribute("nrocorrenciasMesI", nrocorrenciasI);
			

}



public void provinciaTnTIFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProvinciaFiltro2(radioButton, codSelected, projecto, ano);


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
			
			model.addAttribute("nomesProvincias",nomes);
			model.addAttribute("numeroocorencia1", nrocorrenciasT);
			model.addAttribute("numeroocorencia2", nrocorrenciasNT);
			model.addAttribute("numeroocorencia3", nrocorrenciasI);
			

}


public void projectoTnTIFiltro2(Model model, int ano, String radioButton, int codSelected, String projecto){
	
	List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorProjectoFiltro2(radioButton, codSelected, projecto, ano);


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
			
			model.addAttribute("nomesProjecto",nomes);
			model.addAttribute("numeroocorenciaProjecto", nrocorrenciasT);
			model.addAttribute("numeroocorenciaProjecto2", nrocorrenciasNT);
			model.addAttribute("numeroocorenciaProjecto3", nrocorrenciasI);
			

}

// FILTRAR INTERNO

@PostMapping("/filtrar")
public String filtrar(@RequestParam("ano") int ano,
		@RequestParam("radioButton") String radioButton,
		@RequestParam(name="codSelectedSemestre", required = false, defaultValue = "0") int codSelectedSemestre,
		@RequestParam(name="codSelectedTrimestre", required = false, defaultValue = "0" ) int codSelectedTrimestre,
		@RequestParam(name="codSelectedMes", required = false, defaultValue = "0") int codSelectedMes,
		@RequestParam(name="projecto", required = false) String projecto,
		Model model) {
	
	int codSelected=0;
	
	if(radioButton.equals("S")) {
		codSelected=codSelectedSemestre;
	}else if(radioButton.equals("T")) {
		
		codSelected=codSelectedTrimestre;
		
	}else if(radioButton.equals("M")) {
		codSelected=codSelectedMes;
	}
	
	
	model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadasFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoesFiltro2(radioButton, codSelected, projecto, ano));
	
	
	cidadeFiltro2(model, ano, radioButton, codSelected, projecto);
	mesTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	provinciaTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	projectoTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	regiaoFiltro2(model, ano, radioButton, codSelected, projecto);
	canalEntradaFiltro2(model, ano, radioButton, codSelected, projecto);
	canalCategoriaFiltro2(model, ano, radioButton, codSelected, projecto);
	
	
	
	
	String periodo="";
	
	String projecto1="";
	
	if(projecto!="") {
		projecto1="- "+projecto;
	}
	
	
	if(radioButton.equals("S")) {
		periodo=codSelected+ "° Semestre";
	}else if(radioButton.equals("T")) {
		
		periodo=codSelected+ "° Trimestre" ;
		
	}else if(radioButton.equals("M")) {
		if(codSelected==1) {
			periodo="Janeiro";
		}else if(codSelected==2){
			periodo="Fevereiro";
		}else if(codSelected==3){
			periodo="Março";
		}else if(codSelected==4){
			periodo="Abril";
		}else if(codSelected==5){
			periodo="Maio";
		}else if(codSelected==6){
			periodo="Junho";
		}else if(codSelected==7){
			periodo="Julho";
		}else if(codSelected==8){
			periodo="Agosto";
		}else if(codSelected==9){
			periodo="Setembro";
		}else if(codSelected==10){
			periodo="Outubro";
		}else if(codSelected==11){
			periodo="Novembro";
		}else if(codSelected==12){
			periodo="Dezembro";
		}
	}
	
	
	model.addAttribute("dados", "Filtro: "+ ano+" - "+periodo+" "+projecto1);
	
	
	
	
	
	return "reclamacoes/home";
		
		
		
		
	
}

// CHAMA EXTERNO
@PostMapping("/filter")
public String filter(@RequestParam("ano") int ano,
		@RequestParam("radioButton") String radioButton,
		@RequestParam(name="codSelectedSemestre", required = false, defaultValue = "0") int codSelectedSemestre,
		@RequestParam(name="codSelectedTrimestre", required = false, defaultValue = "0" ) int codSelectedTrimestre,
		@RequestParam(name="codSelectedMes", required = false, defaultValue = "0") int codSelectedMes,
		@RequestParam(name="projecto", required = false) String projecto,
		Model model) {

	
	
	
int codSelected=0;
	
	if(radioButton.equals("S")) {
		codSelected=codSelectedSemestre;
	}else if(radioButton.equals("T")) {
		
		codSelected=codSelectedTrimestre;
		
	}else if(radioButton.equals("M")) {
		codSelected=codSelectedMes;
	}
	
	System.out.println("sssssssssssssssssssssssssssssssssssss "+ano);
	System.out.println("sssssssssssssssssssssssssssssssssssss "+radioButton);
	
	model.addAttribute("totalDeOcorrenciasProcedentes", ocorrenciaRepository.totalDeOcorrenciasProcedentesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeReclamacoesProcedentes", ocorrenciaRepository.totalDeReclamacoesProcedentesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeReclamacoesTerminadas", ocorrenciaRepository.totalDeReclamacoesTerminadasFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeOcorrenciasNaoReclamacoes", ocorrenciaRepository.totalDeOcorrenciasNaoReclamacoesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalOcorrencias", ocorrenciaRepository.totalDeOcorrenciasFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeOcorrenciasImprocedentes", ocorrenciaRepository.totalDeOcorrenciasImprocedentesFiltro2(radioButton, codSelected, projecto, ano));
	model.addAttribute("totalDeOcorrenciasPorValidar", ocorrenciaRepository.totalDeOcorrenciasPorValidarFiltro2(radioButton, codSelected, projecto, ano));
	
	
	cidadeFiltro2(model, ano, radioButton, codSelected, projecto);
	mesTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	provinciaTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	projectoTnTIFiltro2(model, ano, radioButton, codSelected, projecto);
	regiaoFiltro2(model, ano, radioButton, codSelected, projecto);
	canalEntradaFiltro2(model, ano, radioButton, codSelected, projecto);
	canalCategoriaFiltro2(model, ano, radioButton, codSelected, projecto);
	
String periodo="";
	
	String projecto1="";
	
	if(projecto!="") {
		projecto1="- "+projecto;
	}
	
	
	if(radioButton.equals("S")) {
		periodo=codSelected+ "° Semestre";
	}else if(radioButton.equals("T")) {
		
		periodo=codSelected+ "° Trimestre" ;
		
	}else if(radioButton.equals("M")) {
		if(codSelected==1) {
			periodo="Janeiro";
		}else if(codSelected==2){
			periodo="Fevereiro";
		}else if(codSelected==3){
			periodo="Março";
		}else if(codSelected==4){
			periodo="Abril";
		}else if(codSelected==5){
			periodo="Maio";
		}else if(codSelected==6){
			periodo="Junho";
		}else if(codSelected==7){
			periodo="Julho";
		}else if(codSelected==8){
			periodo="Agosto";
		}else if(codSelected==9){
			periodo="Setembro";
		}else if(codSelected==10){
			periodo="Outubro";
		}else if(codSelected==11){
			periodo="Novembro";
		}else if(codSelected==12){
			periodo="Dezembro";
		}
	}
	
	
	model.addAttribute("dados", "Filtro: "+ ano+" - "+periodo+" "+projecto1);
	
	
	
	return "publico/estastica";
	
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
	public String preCadastrarOcorrencia(Ocorrencia ocorrencia, ModelMap model, Provincia provincia, RedirectAttributes attr) throws MessagingException {

    	
    	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
    	int ano = Calendar.getInstance().get(Calendar.YEAR);
    	
    	String anoo = String.valueOf(ano);
    	String anooo= anoo.substring(2, 4);
    	
    	String contacto = ocorrencia.getContactoUtente().isEmpty() ? null : ocorrencia.getContactoUtente();
    	String email = ocorrencia.getEmailUtente().isEmpty() ? null : ocorrencia.getEmailUtente();

    	
    	
    		ocorrencia.setGrmStamp(provincia.getCodigo()+""+codigo+""+anooo);
    		ocorrencia.setEstado("Temporario");
    	
    		ocorrencia.setTemporario(true);
    		
    		System.out.println("Antes de salvar");
    		ocorrenciaRepository.save(ocorrencia);

    		String descricao ="Caro Utente, a Sua Ocorrência foi submetida com sucesso.\n" +
    				"NOTA: Anote o seu código para o acompanhamento da sua ocorrência \n"+provincia.getCodigo()+""+codigo+""+anooo;

    		String emaildestino = ocorrencia.getEmailUtente();

    		String assunto = "Confirmação de código de acesso - FNDS";

    		if(email!=null){
    			emailService.enviarEmail(descricao,"FNDS",emaildestino,assunto);
    		}


        	if(contacto!=null){

        		String mensagem = "A sua Ocorrência foi submetido com sucesso, o código para acompanhamento é: "+provincia.getCodigo()+""+codigo+""+anooo;
    			smsService.sendSMS("+258"+ocorrencia.getContactoUtente(),mensagem);

    		}
        	
        	
        	List<User> lista = (List<User>) userRepository.findAll();

        	

    		String emaildest = ocorrencia.getEmailUtente();

    		String assun = "Ocorrência Temporária - FNDS";

    		String localprovincia = ocorrencia.getProvincia().getDesignacao();
    		
         	if(ocorrencia.getEstado().equals("Temporario")) {
         		
         		
         		 for (int i=0;i<lista.size();i++) {
         			String descric = "Sr(a). "+lista.get(i).getNome()+ ". Há uma  Ocorrência Temporária de CODIGO: "+provincia.getCodigo()+""+codigo+""+anooo; 
                	 
               if(localprovincia.equals(lista.get(i).getProvincia().getDesignacao())) {
            	   emailService.enviarEmail(descric,"FNDS", emaildest, assun);
               }
         		
         	}
        	
         	}


    		

    	// model.addAttribute("ocorrenciaa", ocorrencia.getGrmStamp());

    	attr.addFlashAttribute("success", "Preocupação submetida com sucesso.");
    	
    	attr.addFlashAttribute("ocorrenciaa", ocorrenciaService.buscarPorId(ocorrencia.getId()));
    	
    	
    	return "redirect:/";
	}
    
    
    public void cidadeFiltro(Model model,Date datainicial,Date datafinal){
    	
		List<Object[]> lista = ocorrenciaRepository.busqueTudoAgrupadoPorCidadeFiltro1(datainicial, datafinal);


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
    	
    	if(ocorrencia==null) {
    		
    		model.addAttribute("error", "Código inexistente");
    		
    		 return "publico/acompanharQueixa";
    	}
    	
    	
    	
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
