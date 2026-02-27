package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class PerfilEgreso {
    private int id;
    private String descripcion;
    private List<String> habilidadesTransversales;
    private List<String> habilidadesEspecificas;

    public PerfilEgreso() {
    }

    public PerfilEgreso(int id, String descripcion, 
        List<String> habilidadesTransversales, List<String>habilidaesEspecificas){
        this.id = id;
        this.descripcion = descripcion;
        this.habilidadesTransversales = habilidadesTransversales;
        this.habilidadesEspecificas = habilidaesEspecificas;
    }
}
