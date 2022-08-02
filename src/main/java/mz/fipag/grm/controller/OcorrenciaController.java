package mz.fipag.grm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.domain.Ocorrencia;
import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.ProjectoUser;
import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.domain.Resolucao;
import mz.fipag.grm.domain.SubCategoria;
import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.DistritoRepository;
import mz.fipag.grm.repository.DocsRepository;
import mz.fipag.grm.repository.OcorrenciaRepository;
import mz.fipag.grm.repository.PostoAdminitrativoRepository;
import mz.fipag.grm.repository.ProcessoRepository;
import mz.fipag.grm.repository.ProjectoRepository;
import mz.fipag.grm.repository.ProjectoUserRepository;
import mz.fipag.grm.repository.ProvinciaRepository;
import mz.fipag.grm.repository.ResolucaoRepository;
import mz.fipag.grm.repository.ResponsabilidadeRepository;
import mz.fipag.grm.repository.SubCategoriaRepository;
import mz.fipag.grm.repository.UserRepository;
import mz.fipag.grm.service.CategoriaService;
import mz.fipag.grm.service.DistritoService;
import mz.fipag.grm.service.DocStorageService;
import mz.fipag.grm.service.EmailService;
import mz.fipag.grm.service.EmpreiterioService;
import mz.fipag.grm.service.JasperService;
import mz.fipag.grm.service.OcorrenciaService;
import mz.fipag.grm.service.PostoAdministrativoService;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import mz.fipag.grm.service.SMSService;
import mz.fipag.grm.service.TipoAlertaService;
import mz.fipag.grm.service.TipoOcorrenciaService;


@Controller
public class OcorrenciaController {

	@Autowired
	private SMSService smsService;

	 @Autowired
	    ProjectoRepository projectoRepository;
	
	@Autowired
	private EmailService emailService;
	
    @Autowired
    private OcorrenciaService ocorrenciaService;
    
    @Autowired
    private ProvinciaService provinciaService;
    
    @Autowired
    private ProvinciaRepository provinciaRepository;
    
    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

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
    private JasperService service;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private ProcessoRepository processoRepository ;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private ProjectoUserRepository projectoUserRepository;

    @Autowired
    UserRepository userRepository;
    
    private long responsavel;

    @GetMapping("/listar/ocorrencia")
    public String listarOcorrencia(ModelMap model, Authentication authentication, @RequestParam("page") Optional<Integer> page){

        authentication.getName();

        List<Ocorrencia> ocorrencia = null;


        //System.out.println(authentication.getName());

        User userlogado = userRepository.findByUsername(authentication.getName());

        //System.out.println(userlogado.getProvincia().getDesignacao());

        if(!userlogado.getProvincia().getDesignacao().equals("Nacional")){
            ocorrencia = ocorrenciaRepository.buscarOcorrenciasPorUsuariosProvinciaProjecto(userlogado.getProvincia().getId(),userlogado.getId());
        }else{
            ocorrencia = (List<Ocorrencia>) ocorrenciaRepository.findAllOrdenarPorNUmeroOrdem();
        }

        model.addAttribute("pageOcorrencia", ocorrencia);
        model.addAttribute("userlogado", userlogado);

        return "ocorrencia/listarOcorrencia";
    }

    @GetMapping("/ocorrencia/observador")
    public String listarOcorrenciaObservador(ModelMap model, Authentication authentication){

        User userlogado = userRepository.findByUsername(authentication.getName());

        model.addAttribute("pageOcorrencia", ocorrenciaRepository.BuscarOrdemDecrecente());
        model.addAttribute("userlogado", userlogado);

        return "ocorrencia/listarOcorrenciaObservador";
    }

    @GetMapping("/observador/ocorrencia/{id}")
    public String DetalheObservadorOcorrencia(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
        model.addAttribute("anexos", docsRepository.findAllByIdResolucao(id));
        model.addAttribute("resolucoes", resolucaoRepository.findByOcorrencia(id));
        model.addAttribute("resolver", new Resolucao());
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "ocorrencia/observadorDetalheOcorrencia";
    }

    @GetMapping("/relatorio/pdf")
    public void exibirRelatorio09(
            HttpServletResponse response) throws IOException {
        String code = "-8";
        service.addParams("logo", "logoa.PNG");
        //service.addParams("projecto", null);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/grafico")
    public void imprimirRelatorio09(
            HttpServletResponse response) throws IOException {
        String code = "-9";
        service.addParams("logo", "logoa.PNG");
        //service.addParams("projecto", null);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
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
    public String novaOcorrencia(ModelMap model,  Authentication authentication){
    	
    	User userlogado = userRepository.findByUsername(authentication.getName());
    	
    	List<Projecto> projecto = null;
    	
    	projecto = projectoRepository.buscarTodosComSelecao(userlogado.getId());
    	
    	
    	model.addAttribute("projectos", projecto);

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
    @GetMapping("/subcategoria/{id}")
    public String listarSubcategorias(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(subCategoriaRepository.buscarPorId(id));
    }


    @ResponseBody
    @GetMapping("/posto/{id}")
    public String listarPosto(@PathVariable("id") Long id){

        Gson gson=new Gson();

        return gson.toJson(postoAdminitrativoRepository.findAllById(id));
    }
    
    @ResponseBody
    @GetMapping("/ocorrencias")
    public String ocorrencias(){

        Gson gson=new Gson();

        return gson.toJson(ocorrenciaRepository.findAll());
    }


    
    public void enviarSMSAlerta() {
    	 List<User> listaUser = (List<User>) userRepository.findAll();
    	 List<Ocorrencia> listaOcorencia = (List<Ocorrencia>) ocorrenciaRepository.findAll();
    	 
    	 
    	 
    	 Date agora = new Date();
    	 
    	 TimeUnit time = TimeUnit.DAYS;
    	 
    	 
    	 
    	 
    	 for (int i=0;i<listaOcorencia.size();i++) {
        	 
    		 
    		 Date criacao = listaOcorencia.get(i).getCreated();
    		 
    		 long resultado = agora.getTime() - criacao.getTime();
    		 
    		 long diferenca = time.convert(resultado, TimeUnit.MILLISECONDS);
    		 
    		// System.out.println("Duracao " +diferenca);
    		 
        
        	 if (diferenca>5 && listaOcorencia.get(i).getEstado()!="Validado" && listaOcorencia.get(i).getTipoAlerta().getDesignacao().equals("Normal")) {
        		 
	        		 for (int j=0;j<listaUser.size();j++) {
	                	 
	                	 String smsNormal = "Sr(a) "+listaUser.get(j).getNome()+", A ocorrência de código : "+listaOcorencia.get(i).getGrmStamp() +" passou do tempo.";
	              
	                	 
	               if(listaUser.get(j).getTipourgente().equals("Sim") && listaOcorencia.get(i).getProvincia().getDesignacao().equals(listaUser.get(j).getProvincia().getDesignacao())) {
	            	   smsService.sendSMS("+258"+listaUser.get(j).getTelefone(),smsNormal);
	               }
	         	}
        		 
        		 
        	 }else if(diferenca>1 && listaOcorencia.get(i).getEstado()!="Validado" && listaOcorencia.get(i).getTipoAlerta().getDesignacao()!="Normal") {
        		 
        	 
        		 for (int k=0;k<listaUser.size();k++) {
                	 
        			 String smsUrgente = "Sr(a) "+listaUser.get(k).getNome()+", A ocorrência de código : "+listaOcorencia.get(i).getGrmStamp() +" passou do tempo.";
   	              
                	 
  	               if(listaUser.get(k).getTipourgente().equals("Sim") && listaOcorencia.get(i).getProvincia().getDesignacao().equals(listaUser.get(k).getProvincia().getDesignacao())) {
  	            	   smsService.sendSMS("+258"+listaUser.get(k).getTelefone(),smsUrgente);
  	               }
         		
         	}
        	 
        	 }
    		 
    		 
        	 
 	}
    	 
    	 
    }
    

    @PostMapping("/ocorrencias/cadastrar")
	public String salvarOcorrencia(Ocorrencia ocorrencia,Authentication authentication, Provincia provincia, @RequestParam("descricaoAnx") String descricaoNexo, @RequestParam("files") MultipartFile[] files) throws MessagingException {

    	
    	try {
			
    		User userlogado = userRepository.findByUsername(authentication.getName());

            int numeroOrdem = (int) ocorrenciaRepository.BuscarUltimoNumero();
            Integer numeroDeOrdem = numeroOrdem;

            numeroDeOrdem++;

        	int codigo = ThreadLocalRandom.current().nextInt(9, 100);
        	int ano = Calendar.getInstance().get(Calendar.YEAR);
        	
        	String anoo = String.valueOf(ano);
        	String anooo= anoo.substring(2, 4);
        	
        	
        	
        		ocorrencia.setGrmStamp(provincia.getCodigo()+""+codigo+""+anooo);
        		ocorrencia.setRegistado(true);
        		ocorrencia.setNumeroordem(numeroDeOrdem);
        		ocorrencia.setResponsavel(userlogado);
        		ocorrencia.setEstado("Registado");
        		
        		if(ocorrencia.getSexo()==null) {
        			ocorrencia.setSexo("ND");
        		}
        		ocorrenciaService.salvar(ocorrencia);


        	for(MultipartFile file: files) {
        		if(!file.getOriginalFilename().isEmpty()) {
    				
    				docStorageService.saveFile(file, ocorrencia, descricaoNexo);
    			}
            }


            //String localprovincia = ocorrencia.getProvincia().getDesignacao();
            String localprojecto = ocorrencia.getProjecto().getDesignacao();
            
            
            
            long projecto = ocorrencia.getProjecto().getId();

        	 List<User> lista = (List<User>) userRepository.buscarTodosComProjectoSelecionado(projecto);
        	 
        	 List<ProjectoUser> listaProuser = (List<ProjectoUser>) projectoUserRepository.buscarTodosProjectoUserComProjectoSelecionado(projecto);
        	 
        	 
        	// List<ProjectoUser> listaPuser
        	 
        	 
        	 
        	 String contacto = ocorrencia.getContactoUtente().isEmpty() ? null : ocorrencia.getContactoUtente();
     		String email = ocorrencia.getEmailUtente().isEmpty() ? null : ocorrencia.getEmailUtente();



            if(contacto!=null){

                String mensagem = "A sua preocupação foi submetido com sucesso. Código: "+provincia.getCodigo()+""+codigo+""+anooo;
                smsService.sendSMS("+258"+ocorrencia.getContactoUtente(),mensagem);

            }

            if(email!=null){

                String descricao ="Caro Utente, a sua preocupação foi submetida com sucesso.\n" +
                        "NOTA: Anote o seu código para o acompanhamento\n"+provincia.getCodigo()+""+codigo+""+anooo;

                String emaildestino = ocorrencia.getEmailUtente();
                String nome = "A sua preocupação foi submetido com sucesso";

                String assunto = "Confirmação de código de acesso - FNDS";

                emailService.enviarEmail(descricao,nome,emaildestino,assunto);
            }

         	if(ocorrencia.getTipoAlerta().getDesignacao().equals("Urgente")) {
         		
         		 
         		
         		 for (int i=0;i<lista.size();i++) {
         			 
                    String nome = "Preocupação submetida com sucesso";

                    String assunto = "Confirmação de submissão - FNDS";
                	 
                	 String smsurgente = "Há uma ocorrência urgente. Código : "+provincia.getCodigo()+""+codigo+""+anooo;
                	 
                	 
                	 
                	 //if(lista.get(i).getTipourgente().equals("Sim") && (localprovincia.equals(lista.get(i).getProvincia().getDesignacao()) ) || ("Nacional".equals(lista.get(i).getProvincia().getDesignacao()))){
                	// if(lista.get(i).getTipourgente().equals("Sim") && (localprovincia.equals(lista.get(i).getProvincia().getDesignacao()))){
                	 
                	 if(lista.get(i).getTipourgente().equals("Sim")  && (localprojecto.equals(listaProuser.get(i).getProjecto().getDesignacao()))){
            	   smsService.sendSMS("+258"+lista.get(i).getTelefone(),smsurgente);
            	  
            	   emailService.enviarEmail(smsurgente,nome,lista.get(i).getEmail(),assunto);
            	   
            	   
               }
         		
         	}
        	
         	}
         	
         	if(ocorrencia.getTipoAlerta().getDesignacao().equals("GBV")) {
         		
         		
        		 for (int i=0;i<lista.size();i++) {
        			 
                   String nome = "Preocupação submetida com sucesso";

                   String assunto = "Confirmação de submissão - FNDS";
               	 
               	 String smsgbv = "Há uma ocorrência VBG. Código : "+provincia.getCodigo()+""+codigo+""+anooo;
               	 
               	 
               //	if(lista.get(i).getTipogbv().equals("Sim") && (localprovincia.equals(lista.get(i).getProvincia().getDesignacao()) ) || ("Nacional".equals(lista.get(i).getProvincia().getDesignacao()))){
               	 
               	if(lista.get(i).getTipogbv().equals("Sim") && (localprojecto.equals(listaProuser.get(i).getProjecto().getDesignacao()))){
           	   smsService.sendSMS("+258"+lista.get(i).getTelefone(),smsgbv);
           	   emailService.enviarEmail(smsgbv,nome,lista.get(i).getEmail(),assunto);
              }
        		
        	}
       	
        	}
         	
         	if(ocorrencia.getTipoAlerta().getDesignacao().equals("Normal")) {
         		
       		 
         		
       		 for (int i=0;i<lista.size();i++) {
       			 
                  String nome = "Preocupação submetida com sucesso";

                  String assunto = "Confirmação de submissão - FNDS";
              	 
              	 String smsgbv = "Há uma ocorrência com o código : "+provincia.getCodigo()+""+codigo+""+anooo;
              	 
              	 
             if(lista.get(i).getTipogbv().equals("Sim") && (localprojecto.equals(listaProuser.get(i).getProjecto().getDesignacao()))) {
          	   emailService.enviarEmail(smsgbv,nome,lista.get(i).getEmail(),assunto);
             }
       		
       	}
      	
       	}
         	
    	
    	} catch (Exception e) {
			System.out.println("MENSAGEM: "+e.getStackTrace());
			System.out.println("MENSAGEM: "+e.getMessage());
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
	  public String editarCategoria(Ocorrencia ocorrencia,Authentication authentication, @RequestParam("descricaoAnx") String descricaoNexo, @RequestParam("files") MultipartFile[] files) {
    	
    	User userlogado = userRepository.findByUsername(authentication.getName());
    	
    		ocorrencia.setResponsavel(userlogado);
    		ocorrencia.setRegistado(true);
    		ocorrencia.setEstado("Registado");
    		ocorrenciaService.editar(ocorrencia);

        for(MultipartFile file: files) {
            if(!file.getOriginalFilename().isEmpty()) {

                docStorageService.saveFile(file, ocorrencia, descricaoNexo);
            }
        }
		  
		  return "redirect:/listar/ocorrencia"; 
	  }
    
    @GetMapping("/ocorrencias/editar/{id}") 
	  public String vistaEditarOcorrencia(@PathVariable("id") Long id, ModelMap model) {
	  
    	model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
	  
	  return "ocorrencia/editarOcorrencia"; 
	  }

    @GetMapping("/resolver/ocorrencia/{id}")
    public String resolverOcorrencia(@PathVariable("id") Long id, ModelMap model) {
    	
    	Ocorrencia ocorrenciaProcesso = ocorrenciaService.buscarPorId(id);

        
        model.addAttribute("ocorrencia", ocorrenciaService.buscarPorId(id));
        model.addAttribute("anexos", docsRepository.findAllByIdResolucao(id));
        model.addAttribute("resolucoes", resolucaoRepository.findByOcorrencia(id));
        model.addAttribute("editarResolucao", resolucaoRepository.ultimaResolucao(id));
        model.addAttribute("resolver", new Resolucao());
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "ocorrencia/resolucao";
    }

    @GetMapping("/editar/acompanhamento/{id}")
    public String editarAcompanhamento(@PathVariable("id") Long id, ModelMap model) {

        //Resolucao
        model.addAttribute("resolver", resolucaoRepository.findById(id));
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "ocorrencia/editarAcompanhamento";
    }

    @GetMapping("/editar/resolucao/{id}")
    public String editarResolucao(@PathVariable("id") Long id, ModelMap model) {

        //Resolucao
        model.addAttribute("resolver", resolucaoRepository.findById(id));
        model.addAttribute("responsaveis", responsabilidadeRepository.findAll());

        return "ocorrencia/editarResolucao";
    }

    @PostMapping("/resolucao/editar")
    public String resolverEditar(Resolucao resolucao,Ocorrencia ocorrencia, @RequestParam boolean report) {


        if(report == true){

            resolucao.setOcorrencia(ocorrencia);
            resolucaoRepository.save(resolucao);
            ocorrencia.setResolucao("T");
            ocorrenciaService.salvar(ocorrencia);


        }else{

            if(resolucao.getResponsabilidade().getId() == 4){
                ocorrencia.setResolucao("A");
                resolucao.setTipo("A");
                resolucao.setDesignacao(resolucao.getMotivo());
            }

            resolucaoRepository.save(resolucao);

        }

        return "redirect:/resolver/ocorrencia/"+resolucao.getOcorrencia().getId();
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
            resolucao.setTipo("T");
            resolucaoRepository.save(resolucao);
            ocorrencia.setResolucao("T");
            ocorrenciaService.salvar(ocorrencia);

        }else{
        	   if(resolucao.getResponsabilidade().getId() == 4){
                   ocorrencia.setResolucao("A");
                   ocorrencia.setResolucao("A");
                   resolucao.setTipo("A");
                   resolucao.setDesignacao(resolucao.getMotivo());

                   
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
    public String validacaoVista(Ocorrencia ocorrencia, RedirectAttributes redirAttrs,
    		@RequestParam String procedencia, @RequestParam("descricao") String descricaoAnexo, Authentication authentication, @RequestParam("files") MultipartFile[] files) throws MessagingException {


        User userlogado = userRepository.findByUsername(authentication.getName());


    		ocorrencia.setProcedencia(procedencia);
    		ocorrencia.setValidado(true);
    		//ocorrencia.setNumeroordem(numeroDeOrdem);
    		ocorrencia.setResponsavel(userlogado);
        	ocorrencia.setEstado("Validado");
        	ocorrencia.setResolucao("V");
           // ocorrenciaService.editar(ocorrencia);
            ocorrenciaRepository.save(ocorrencia);
            
            for(MultipartFile file: files) {
        		if(!file.getOriginalFilename().isEmpty()) {
    				
    				docStorageService.saveFileValidacao(file, ocorrencia, descricaoAnexo);
    			}
            }
            
            
            
            
            if(procedencia.equals("Não")){
            	
            	
            	String contacto = ocorrencia.getContactoUtente().isEmpty() ? null : ocorrencia.getContactoUtente();
         		String email = ocorrencia.getEmailUtente().isEmpty() ? null : ocorrencia.getEmailUtente();

                if(contacto!=null) {

                    String smsNaoprocede = "Caro Utente a sua ocorrência não procede, consulte o código: "+ocorrencia.getGrmStamp();
                    smsService.sendSMS("+258" + ocorrencia.getContactoUtente(), smsNaoprocede);

                }
                if(email!=null) {

                    String descricao = "A sua Ocorrência com o codigo: "+ocorrencia.getGrmStamp()+" não procede. MOTIVO: "+ocorrencia.getObservacao();
                    String nome = "A sua Ocorrência não procede";
                    String destino = ocorrencia.getEmailUtente();
                    String assunto = "Resultado da validação da Ocorrência";

                    emailService.enviarEmail(descricao,nome,destino,assunto);
                }
            }
            


         	//String localprovincia = ocorrencia.getProvincia().getDesignacao();
            String localprojecto = ocorrencia.getProjecto().getDesignacao();
         	
         	
         	
            long projecto = ocorrencia.getProjecto().getId();

            List<User> lista = (List<User>) userRepository.buscarTodosComProjectoSelecionado(projecto);
            
            List<ProjectoUser> listaProuser = (List<ProjectoUser>) projectoUserRepository.buscarTodosProjectoUserComProjectoSelecionado(projecto);
         	
         	 if(procedencia.equals("Sim")){
         	
         	if(ocorrencia.getTipoAlerta().getDesignacao().equals("Urgente") || ocorrencia.getTipoAlerta().getDesignacao().equals("GBV")) {
         		
         		String nome = "Preocupação submetida com sucesso";

                String assunto = "Confirmação de submissão - FNDS";
         		
         		 for (int i=0;i<lista.size();i++) {
                	 
                	 String smsurgente = "A ocorrência urgente "+ocorrencia.getGrmStamp() +" é procedente!";
                	 String smsgbv = "A ocorrência GBV "+ocorrencia.getGrmStamp() +" é procedente!";
                	 
              // if(lista.get(i).getTipourgente().equals("Sim") && (localprovincia.equals(lista.get(i).getProvincia().getDesignacao())) || ("Nacional".equals(lista.get(i).getProvincia().getDesignacao()))) {
                	 if(lista.get(i).getTipourgente().equals("Sim")  && (localprojecto.equals(listaProuser.get(i).getProjecto().getDesignacao()))){
                	
                		 
                		 
            	   smsService.sendSMS("+258"+lista.get(i).getTelefone(),smsurgente);
            	   emailService.enviarEmail(smsurgente,nome,lista.get(i).getEmail(),assunto);
            	   
               }else 
            	   
            	  // if(lista.get(i).getTipogbv().equals("Sim") && (localprovincia.equals(lista.get(i).getProvincia().getDesignacao())) || ("Nacional".equals(lista.get(i).getProvincia().getDesignacao()))) {
            	   if(lista.get(i).getTipogbv().equals("Sim")  && (localprojecto.equals(listaProuser.get(i).getProjecto().getDesignacao()))){
            	   smsService.sendSMS("+258"+lista.get(i).getTelefone(),smsgbv);
            	   emailService.enviarEmail(smsgbv,nome,lista.get(i).getEmail(),assunto);
               }
         		
         	}
        	
         	}
         	 }
         	
         	
         	

            //return "redirect:/resolver/ocorrencia/"+ocorrencia.getId();
         	return "redirect:/listar/ocorrencia";

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
    
    
    
    
    @PostMapping("/busca")
    public String filter1(@RequestParam("projecto") String projecto,
    						@RequestParam(name="datainicial", required = false) 
    						@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datainicial,
    						@RequestParam(name="datafinal", required = false) 
    						@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datafinal,
    						
    						Model model) {

    	String stringDatIncial="";
    	String stringDatFinal="";
    	
    	String projecto1="";
    	if(projecto!="") {
			projecto1="- "+projecto;
		}

    	
    	SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    	
    	
    	
    	if(datainicial!=null && datafinal!=null) {
    		stringDatIncial= DateFor.format(datainicial);
    		stringDatFinal= DateFor.format(datafinal);
    		
    	}
		
		model.addAttribute("pageOcorrencia", ocorrenciaRepository.totalDeOcorrenciasPorDataseProjecto(datainicial, datafinal, projecto));
		model.addAttribute("dados1", "De "+ stringDatIncial+" a "+stringDatFinal + " "+ projecto1);

    		 return "ocorrencia/listarOcorrencia";
    	
    }
    

    @ModelAttribute("provincias")
	public List<Provincia> listaDeDePronvicias() {
		return provinciaRepository.findAllOrderById();
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
	
	@ModelAttribute("subcategorias")
	public List<SubCategoria> listaDesubCategorias(){
		return (List<SubCategoria>) subCategoriaRepository.findAll();
	}


}
