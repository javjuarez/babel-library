package com.example.babellibrary.models.dao.interfaces;

import java.util.List;

import com.example.babellibrary.models.entity.Estante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstanteDaoInterface extends JpaRepository<Estante, Long> {

    @Query(value = "SELECT e.estante FROM estantes e JOIN libreros l ON e.librero_id = l.id WHERE l.librero = ?1", nativeQuery = true)
    public List<Object[]> findByLibreroLibrero(String librero);
    
}