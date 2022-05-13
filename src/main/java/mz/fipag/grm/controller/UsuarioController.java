package mz.fipag.grm.controller;

import mz.fipag.grm.domain.ProjectoUser;
import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.ProjectoUserRepository;
import mz.fipag.grm.repository.RegiaoRepository;
import mz.fipag.grm.repository.RoleRepository;
import mz.fipag.grm.repository.UserRepository;
import mz.fipag.grm.service.ProjectoService;
import mz.fipag.grm.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UserRepository userRepository;

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
    private RegiaoRepository regiaoRepository;

    @GetMapping("/registar/usuarios")
    public String registarUsuarios(ModelMap model){

        model.addAttribute("user", new User());
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());
        model.addAttribute("projectos", projectoService.buscarTodos());

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
            user.setTipogbv("Não");
        }

        if(user.getTipogbv()==null){
            user.setTipogbv("Não");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);


        for(long projecto: projectos) {

                Projecto projecto1 = projectoService.buscarPorId(projecto);

                projectoUser.setProjecto(projecto1);
                projectoUser.setUser(user);
                projectoUserRepository.save(projectoUser);

        }

        return "redirect:/listar/usuarios";
    }

	/*
	 * @ModelAttribute("cidades") public List<Cidade> listaDeCidades(){ return
	 * cidadeService.buscarTodos(); }
	 */

    @GetMapping("/usuarios/editar/{id}")
    public String vistaEditarUsuario(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("user", userRepository.findById(id));
        model.addAttribute("userProjectos",projectoUserRepository.buscarPorUser(id));
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());
        model.addAttribute("projectos", projectoService.buscarTodos());

        return "usuarios/editarUsuarios";
    }

    @PostMapping("/editar/usuarios")
    public String editarUsuario(User user, ProjectoUser projectoUser, @RequestParam("projectos") long projectos[], RedirectAttributes attr) {

    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        for(long projecto: projectos) {

            Projecto projecto1 = projectoService.buscarPorId(projecto);

            projectoUser.setProjecto(projecto1);
            projectoUser.setUser(user);
            projectoUserRepository.save(projectoUser);

        }

       // attr.addFlashAttribute("success","Categoria editada com sucesso.");

        return "redirect:/listar/usuarios";
    }

}
