package com.example.babellibrary.models.dao.interfaces;

import com.example.babellibrary.models.entity.Libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibroDaoInterface extends PagingAndSortingRepository<Libro, Long> {

    @Query(value = "select l from Libro l where l.titulo like %?1% or l.autor like %?1% or l.editorial like %?1%")
    public Page<Libro> findByTituloOrAutorOrEditorial(String term, Pageable pageable);

}