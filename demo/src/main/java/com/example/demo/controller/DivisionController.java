package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Division;
import com.example.demo.repository.DivisionesRepository;

import jakarta.validation.Valid;

@Controller
public class DivisionController {

    @Autowired
    private DivisionesRepository repo;

    // LISTADO
    @GetMapping("/consola/divisiones")
    public String listarDivisiones(Model model) {

        List<Division> divisiones = repo.findAll();
        model.addAttribute("divisiones", divisiones);

        // 🔥 IMPORTANTE: siempre enviar objeto vacío
        model.addAttribute("division", new Division());

        return "divisionTabla";
    }

    // OBTENER POR ID (AJAX)
    @GetMapping("/consola/divisiones/{id}")
    @ResponseBody
    public Division obtenerDivision(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("División no encontrada"));
    }

    // GUARDAR
    @PostMapping("/consola/divisiones/save")
    public String guardarDivision(
            @Valid @ModelAttribute("division") Division division,
            Errors errors,
            Model model) {

        // 🔥 SI HAY ERRORES
        if (errors.hasErrors()) {
            model.addAttribute("divisiones", repo.findAll());
            model.addAttribute("division", division); // MUY IMPORTANTE
            return "divisionTabla";
        }

        // DEBUG (puedes dejarlo)
        System.out.println("ID: " + division.getId());
        System.out.println("Clave: " + division.getClave());
        System.out.println("Nombre: " + division.getNombre());
        System.out.println("Activo: " + division.getActivo());

        repo.save(division);

        return "redirect:/consola/divisiones";
    }
}