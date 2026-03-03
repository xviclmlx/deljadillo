package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class OfertaEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "El nombre de la oferta es obligatorio")
    private String nombreOferta;

    private String modalidad;

    @NotEmpty(message = "La URL de la imagen es obligatoria")
    @Size(min = 2, max = 255, message = "La URL de la imagen debe tener entre 2 y 255 caracteres")
    private String imagen;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_division")
    @JsonBackReference // 🔥 evita ciclos infinitos
    private Division division;
}
