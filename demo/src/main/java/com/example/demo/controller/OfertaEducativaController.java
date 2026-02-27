package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Division;
import com.example.demo.model.OfertaEducativa;
import com.example.demo.repository.DivisionesRepository;
import com.example.demo.repository.OfertaEducativaRepository;

import jakarta.validation.Valid;

@Controller
public class OfertaEducativaController {

    @Autowired
    private OfertaEducativaRepository repo;

    @Autowired
    private DivisionesRepository divrepo;

    // List<OfertaEducativa> ofertas = new ArrayList<>();

    @GetMapping("/ofertaEducativa/tabla")
    public String ofertaTabla (Model model) {
        List<OfertaEducativa> ofertas = repo.findAll();
        model.addAttribute("ofertas", ofertas);

        List<Division> divisiones = divrepo.findAll();
        model.addAttribute("divisiones", divisiones);

        return "ofertaEducativaTabla";
    }

    @GetMapping("/consola/oferta-list")
    public String ofertaConsola (Model model) {
        List<OfertaEducativa> ofertas = repo.findAll();
        model.addAttribute("ofertas", ofertas);

        List<Division> divisiones = divrepo.findAll();
        model.addAttribute("divisiones", divisiones);

        return "ofertaEducativaTabla";
    }

    @GetMapping("/consola/ofertaEducativa/add")
    public String getFormulario(Model model, OfertaEducativa ofertaEducativa){
        //model.addAttribute(ofertas);
        //ofertaEducativa.setId(ofertas.getLast().getId() +1);
       // ofertaEducativa.setPerfilDeIngreso(new PerfilDeIngreso());
       List<Division> divisiones = divrepo.findAll();
        model.addAttribute("divisiones", divisiones);
        
        List<OfertaEducativa> ofertas = repo.findAll();
        model.addAttribute("ofertas", ofertas);
        
        return "ofertaEducativaForm";
    }
    
    @GetMapping("/consola/ofertaEducativa/edit/{id}")
    public String getEditOferta(Model model, OfertaEducativa ofertaEducativa, @PathVariable("id") int id){
        //model.addAttribute(ofertas);
        //ofertaEducativa.setId(ofertas.getLast().getId() +1);
       // ofertaEducativa.setPerfilDeIngreso(new PerfilDeIngreso());
        ofertaEducativa = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("oferta no encontrada"));
        model.addAttribute("ofertaEducativa", ofertaEducativa);

        List<Division> divisiones = divrepo.findAll();
        model.addAttribute("divisiones", divisiones);
        return "ofertaEducativaForm";
    }

    @PostMapping("/consola/ofertaEducativa/save")
    public String postMethodName(@Valid OfertaEducativa ofertaEducativa, Errors errors, Model model) {
        if (errors.hasErrors()) {

            List<Division> divisiones = divrepo.findAll();
            model.addAttribute("divisiones", divisiones);


        return "ofertaEducativaForm";
    }
       // ofertas.add(ofertaEducativa);
       repo.save(ofertaEducativa);
        return "redirect:/ofertaEducativa";
    }

    @GetMapping("/ofertaEducativa")
    public String oferta (Model model){

    List<OfertaEducativa> ofertas = repo.findAll();

    /*  if (ofertas.isEmpty()){

        OfertaEducativa oferta1 = new OfertaEducativa();
        oferta1.setId(1);
        oferta1.setNombreOferta("Ingeniería Mecatrónica");
        oferta1.setModalidad("Modalidad intensiva y mixta");
        oferta1.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/ME.png");
        oferta1.setPerfilDeIngreso(
            new PerfilDeIngreso(
            1, 
            "El egresado de educación media superior deberá contar con un conjunto integral de conocimientos, habilidades, actitudes y valores que sean deseables para ser considerado como candidato apto para cursar estudios de nivel superior con la finalidad de garantizar su formación al terminar sus estudios profesionales.",
            List.of("Habilidad para indagar, analizar y transmitir información procedente de diversas fuentes", 
                    "Habilidad para escuchar, interpretar y expresar mensajes en distintos contextos."), 
            List.of("Deseable con habilidades en lógica de programación.", 
                    "Motivación por la innovación y nuevas tecnologías.")
        )); 
        
        ofertas.add(oferta1);

        OfertaEducativa oferta2 = new OfertaEducativa();
        oferta2.setId(2);
        oferta2.setNombreOferta("Ingeniería en Tecnologías de la Inormación e Innovación");
        oferta2.setModalidad("");
        oferta2.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LTII.png");
       oferta2.setPerfilDeIngreso(
            new PerfilDeIngreso(
                2, 
                null, 
                null, 
                null));  

        ofertas.add(oferta2);

        OfertaEducativa oferta3 = new OfertaEducativa();
        oferta3.setId(3);
        oferta3.setNombreOferta("Ingeniería en Energía y Desarrollo Sostenible");
        oferta3.setModalidad("");
        oferta3.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIEDS2.png");
         oferta3.setPerfilDeIngreso(
            new PerfilDeIngreso(
                3, 
                null, 
                null, 
                null));  

        ofertas.add(oferta3);

            } */
        model.addAttribute("ofertas", ofertas);


    return "ofertaEducativa";
}
}
