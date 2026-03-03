package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Division;
import com.example.demo.model.OfertaEducativa;
import com.example.demo.repository.DivisionesRepository;
import com.example.demo.repository.OfertaEducativaRepository;

@Controller
public class OfertaEducativaController {

    @Autowired
    private OfertaEducativaRepository repo;

    @Autowired
    private DivisionesRepository divrepo;

    // LISTADO PARA VISTA THYMELEAF
    @GetMapping("/ofertaEducativa/tabla")
    public String ofertaTabla(Model model) {
        List<OfertaEducativa> ofertas = repo.findAll();
        model.addAttribute("ofertas", ofertas);

        List<Division> divisiones = divrepo.findAll();
        model.addAttribute("divisiones", divisiones);

        return "ofertaEducativaTabla";
    }

    // ENDPOINTS AJAX (JSON) → usados por tu JS
    @GetMapping("/consola/ofertas")
    @ResponseBody
    public List<OfertaEducativa> listarOfertasAjax() {
        return repo.findAll();
    }

    @GetMapping("/consola/ofertas/{id}")
    @ResponseBody
    public OfertaEducativa obtenerOferta(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oferta no encontrada"));
    }

    @PostMapping("/consola/ofertas/update")
    @ResponseBody
    public OfertaEducativa actualizarOferta(@RequestBody OfertaEducativa oferta) {
        return repo.save(oferta);
    }

    @DeleteMapping("/consola/ofertas/{id}")
    @ResponseBody
    public void eliminarOferta(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
