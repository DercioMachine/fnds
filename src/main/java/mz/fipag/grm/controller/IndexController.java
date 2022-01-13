package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "login";
    }

    @GetMapping("/home")
    public String home(){

        return "reclamacoes/home";
    }
}