package com.example.babellibrary.models.services.interfaces;

import java.util.Date;
import java.util.List;

import com.example.babellibrary.models.entity.Libro;
import com.example.babellibrary.models.entity.Sala;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroServiceInterface {

    public List<Libro> findAll();

    public Page<Libro> findAll(Pageable pageable);

    public Libro findOne(Long id);

    public List<Libro> findLibroByTitulo(String titulo);

    public List<Libro> findLibroByAutor(String autor);

    public List<Libro> findLibroByEditorial(String editorial);

    public List<Libro> findLibroByFechaPublicacion(Date fechaPublicacion);

    public void save(Libro libro);

    public void delete(Long id);

    public List<Sala> getSalas();

    public List<Object[]> findLibreroBySala(String sala);

    public List<Object[]> findEstanteByLibrero(String librero);

    public Page<Libro> findByTituloOrAutorOrEditorial(String term, Pageable pageable);

}