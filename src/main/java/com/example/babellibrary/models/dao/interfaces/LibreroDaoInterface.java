package com.example.babellibrary.models.dao.interfaces;

import java.util.List;

import com.example.babellibrary.models.entity.Librero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibreroDaoInterface extends JpaRepository<Librero, Long> {

    @Query(value = "SELECT l.librero FROM libreros l JOIN salas s ON l.sala_id = s.id WHERE s.sala = ?1", nativeQuery = true)
    public List<Object[]> findBySalaSala(String sala);
    
}