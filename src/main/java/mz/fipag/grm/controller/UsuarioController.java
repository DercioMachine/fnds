package mz.fipag.grm.controller;

import mz.fipag.grm.domain.ProjectoUser;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.*;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UsuarioController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectoRepository projectoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ProjectoService projectoService;

    @Autowired
    private ProjectoUserRepository projectoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    ResponsabilidadeRepository responsabilidadeRepository;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @GetMapping("/registar/usuarios")
    public String registarUsuarios(ModelMap model){

        model.addAttribute("user", new User());
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());
        model.addAttribute("projectos", projectoService.buscarTodos());
        model.addAttribute("projectos", projectoService.buscarTodos());
        model.addAttribute("niveis", responsabilidadeRepository.findAll());

        return "usuarios/cadastrarUsuarios";
    }

    @GetMapping("/listar/usuarios")
    public String listarUsuarios(ModelMap model){

        model.addAttribute("usuarios", userRepository.findAll());

        return "usuarios/listarUsuarios";
    }

    @GetMapping("/perfil/usuarios")
    public String perfilUsuarios(ModelMap model, Authentication authentication){

        User userlogado = userRepository.findByUsername(authentication.getName());

        model.addAttribute("usuario", userlogado);

        return "usuarios/perfilUsuarios";
    }

    @GetMapping("/alterar/usuarios")
    public String alterarUsuarios(ModelMap model, Authentication authentication){

        User userlogado = userRepository.findByUsername(authentication.getName());

        model.addAttribute("user", userlogado);
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());

        return "usuarios/altearUsuarios";
    }

    @GetMapping("/listar/perfils")
    public String listarPerfils(ModelMap model){

        model.addAttribute("perfils", roleRepository.findAll());

        return "usuarios/listarRoles";
    }

    @PostMapping("/alterar/senha")
    public String alterarSenha(User user,Authentication authentication, ModelMap model, @RequestParam("novaSenha") String novaSenha){

        User userlogado = userRepository.findByUsername(authentication.getName());

            user.setPassword(passwordEncoder.encode(novaSenha));
            userRepository.save(user);
            model.addAttribute("success", "success");

        return "usuarios/altearUsuarios";
    }

    @PostMapping("/cadastrar/usuarios")
    public String cadastrarUsuarios(User user, ProjectoUser projectoUser, @RequestParam("projectos") long projectos[]){

        Regiao regiaonacional = regiaoRepository.findByDesignacao();

        if(user.getTipourgente()==null){
            user.setTipourgente("Não");
        }

        if(user.getTipogbv()==null){
            user.setTipogbv("Não");
        }

        if(user.getRelatoriomensal()==null){
            user.setRelatoriomensal("Não");
        }

        if(user.getTiponormal()==null){
            user.setRelatoriomensal("Não");
        }


        try{

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

            for(long projecto: projectos) {

                Projecto projecto1 = projectoService.buscarPorId(projecto);

                projectoUser.setProjecto(projecto1);
                projectoUser.setUser(user);
                projectoUserRepository.save(projectoUser);

            }

        } catch (Exception e) {
            System.out.println("MENSAGEM: "+e.getStackTrace());
            System.out.println("MENSAGEM: "+e.getMessage());
        }


        return "redirect:/listar/usuarios";
    }

	/*
	 * @ModelAttribute("cidades") public List<Cidade> listaDeCidades(){ return
	 * cidadeService.buscarTodos(); }
	 */

    @GetMapping("/usuarios/apagar/{id}")
    public String apagarProjectoUser(@PathVariable("id") Long id, ModelMap model) {

        ProjectoUser user1 = projectoUserRepository.buscarPoriD(id);
        User user2 = user1.getUser();
        projectoUserRepository.deleteById(user1.getId());

        return "redirect:/usuarios/editar/"+user2.getId();

    }

    @GetMapping("/usuarios/editar/{id}")
    public String vistaEditarUsuario(@PathVariable("id") Long id, ModelMap model) {

        User userSenha = userRepository.buscarPorIdUser(id);

        model.addAttribute("user", userRepository.findById(id));
        model.addAttribute("userid", id);
        model.addAttribute("senha", userSenha.getPassword());
        model.addAttribute("projectosusers",projectoUserRepository.buscarPorUser(id));
        model.addAttribute("userprojectos",new ProjectoUser());
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaRepository.findAllOrderById());
        model.addAttribute("projectos", projectoRepository.buscarTodosSemSelecao(id));
        model.addAttribute("niveis", responsabilidadeRepository.findAll());
        
        //System.out.println("PPPPPRRRRROOOOOJJJJEEEEEECCCTTTOOOOO = " + projectoRepository.buscarTodosSemSelecao(id));

        return "usuarios/editarUsuarios";
    }

    @PostMapping("/cadastrar/projecto")
    public String projectoUser(ProjectoUser projectoUser,@RequestParam("user") long user, RedirectAttributes attr) {

        User user1 = userRepository.buscarPorIdUser(user);
        projectoUser.setUser(user1);
        projectoUserRepository.save(projectoUser);

        return "redirect:/usuarios/editar/"+user1.getId();
    }

    @PostMapping("/editar/usuarios")
    public String editarUsuario(User user, ProjectoUser projectoUser, @RequestParam("password") String password, RedirectAttributes attr) {

        if(user.getTipourgente()==null){
            user.setTipourgente("Não");
        }

        if(user.getTipogbv()==null){
            user.setTipogbv("Não");
        }

        if(user.getRelatoriomensal()==null){
            user.setRelatoriomensal("Não");
        }

        if(user.getTiponormal()==null){
            user.setRelatoriomensal("Não");
        }

     try{
            user.setPassword(password);
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println("MENSAGEM: "+e.getStackTrace());
            System.out.println("MENSAGEM: "+e.getMessage());
        }

        return "redirect:/listar/usuarios";
    }

}
