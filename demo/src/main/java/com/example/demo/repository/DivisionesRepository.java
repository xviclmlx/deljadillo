package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Division;

public interface DivisionesRepository extends JpaRepository<Division, Integer> {

}
