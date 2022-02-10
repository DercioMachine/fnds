package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.domain.Regiao;
import mz.fipag.grm.domain.User;
import mz.fipag.grm.repository.RegiaoRepository;
import mz.fipag.grm.repository.RoleRepository;
import mz.fipag.grm.repository.UserRepository;
import mz.fipag.grm.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @GetMapping("/registar/usuarios")
    public String registarUsuarios(ModelMap model){

        model.addAttribute("user", new User());
        model.addAttribute("perfils", roleRepository.findAll());
        model.addAttribute("regioes", regiaoRepository.findAll());

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
    public String cadastrarUsuarios(User user, @RequestParam("tipo") String tipo){

        user.setTipo(tipo);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/listar/usuarios";
    }

    @ModelAttribute("cidades")
    public List<Cidade> listaDeCidades(){
        return cidadeService.buscarTodos();
    }

}
