package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OfertaEducativa;

public interface OfertaEducativaRepository extends JpaRepository<OfertaEducativa, Integer> { //Integer se pone por el tipo de dato que sea la primary key

}
