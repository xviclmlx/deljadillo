package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PerfilDeIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY es para que en la base de datos sea autoincrementable
    private int id;
    private String descripcion;
    private List<String> habilidadesTransversales;
    private List<String> habilidadesEspecificas;

    public PerfilDeIngreso() {
    }

    public PerfilDeIngreso(int id, String descripcion, 
        List<String> habilidadesTransversales, List<String>habilidaesEspecificas){
        this.id = id;
        this.descripcion = descripcion;
        this.habilidadesTransversales = habilidadesTransversales;
        this.habilidadesEspecificas = habilidaesEspecificas;
    }
}
