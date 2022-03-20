package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.RegiaoRepository;
import mz.fipag.grm.repository.RoleRepository;
import mz.fipag.grm.repository.UserRepository;
import mz.fipag.grm.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @GetMapping("/registar/usuarios")
    public String registarUsuarios(ModelMap model){

        model.addAttribute("user", new User());
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());

        return "usuarios/cadastrarUsuarios";
    }

    @GetMapping("/listar/usuarios")
    public String listarUsuarios(ModelMap model){

        model.addAttribute("usuarios", userRepository.findAll());

        return "usuarios/listarUsuarios";
    }

    @GetMapping("/listar/perfils")
    public String listarPerfils(ModelMap model){

        model.addAttribute("perfils", roleRepository.findAll());

        return "usuarios/listarRoles";
    }

    @PostMapping("/cadastrar/usuarios")
    public String cadastrarUsuarios(User user){

        Regiao regiaonacional = regiaoRepository.findByDesignacao();

        if(user.getTipourgente()==null){
            user.setTipogbv("Não");
        }

        if(user.getTipogbv()==null){
            user.setTipogbv("Não");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/listar/usuarios";
    }
/*
    @ModelAttribute("cidades")
    public List<Cidade> listaDeCidades(){
        return cidadeService.buscarTodos();
    }

    @GetMapping("/usuarios/editar/{id}")
    public String vistaEditarUsuario(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("user", userRepository.findById(id));
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("provincias", provinciaService.buscarTodos());

        return "usuarios/editarUsuarios";
    }*/

    @PostMapping("/editar/usuarios")
    public String editarUsuario(User user, RedirectAttributes attr) {

        userRepository.save(user);

       // attr.addFlashAttribute("success","Categoria editada com sucesso.");

        return "redirect:/listar/usuarios";
    }

}
