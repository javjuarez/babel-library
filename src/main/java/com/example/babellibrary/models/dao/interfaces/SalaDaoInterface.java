package com.example.babellibrary.models.dao.interfaces;

import com.example.babellibrary.models.entity.Sala;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaDaoInterface extends JpaRepository<Sala, Long> {
    
}