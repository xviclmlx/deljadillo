package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Division;
import com.example.demo.repository.DivisionesRepository;

@Controller
public class DivisionController {

    @Autowired
    private DivisionesRepository repo;

    // LISTADO
    @GetMapping("/consola/divisiones")
    public String listarDivisiones(Model model) {

        List<Division> divisiones = repo.findAll();
        model.addAttribute("divisiones", divisiones);

        return "divisionTabla";
    }

    // OBTENER POR ID (AJAX)
    @GetMapping("/consola/divisiones/{id}")
    @ResponseBody
    public Division obtenerDivision(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("División no encontrada"));
    }

    // GUARDAR / ACTUALIZAR (AJAX)
    @PostMapping("/consola/divisiones/update")
    @ResponseBody
    public Division actualizarDivision(@RequestBody Division division) {

        Division guardada = repo.save(division);

        return guardada; // JSON
    }
}
