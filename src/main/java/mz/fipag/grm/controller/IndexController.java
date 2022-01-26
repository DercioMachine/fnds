package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String index(){

        return "login";
    }

    @GetMapping("/")
    public String homeIndex(){

        return "layout";
    }

    @GetMapping("/home")
    public String home(){

        return "reclamacoes/home";
    }
    
}
