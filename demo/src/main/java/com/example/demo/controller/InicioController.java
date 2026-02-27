package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping ("/")
    public String inicio(Model model){
        model.addAttribute("titulo", "bienvenido a mi primera página");
        return "index";
    }

    @GetMapping ("/otro")
    public String otro(Model model){
        model.addAttribute("titulo", "otra página");
        return "otro";
    }
}
