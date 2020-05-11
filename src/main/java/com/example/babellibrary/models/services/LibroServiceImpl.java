package com.example.babellibrary.models.services;

import java.util.Date;
import java.util.List;

import com.example.babellibrary.models.dao.interfaces.EstanteDaoInterface;
import com.example.babellibrary.models.dao.interfaces.LibreroDaoInterface;
import com.example.babellibrary.models.dao.interfaces.LibroDaoInterface;
import com.example.babellibrary.models.dao.interfaces.SalaDaoInterface;
import com.example.babellibrary.models.entity.Libro;
import com.example.babellibrary.models.entity.Sala;
import com.example.babellibrary.models.services.interfaces.LibroServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServiceImpl implements LibroServiceInterface {

    @Autowired
    private LibroDaoInterface libroDao;

    @Autowired
    private SalaDaoInterface salaDao;

    @Autowired
    private LibreroDaoInterface libreroDao;

    @Autowired
    private EstanteDaoInterface estanteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return (List<Libro>) libroDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> findAll(Pageable pageable) {
        return libroDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Libro findOne(Long id) {
        return libroDao.findById(id).orElse(null);
    }

    @Override
    public List<Libro> findLibroByTitulo(String titulo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Libro> findLibroByAutor(String autor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Libro> findLibroByEditorial(String editorial) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Libro> findLibroByFechaPublicacion(Date fechaPublicacion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public void save(Libro libro) {
        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        libroDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sala> getSalas() {
        return salaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findLibreroBySala(String sala) {
        return libreroDao.findBySalaSala(sala);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findEstanteByLibrero(String librero) {
        return estanteDao.findByLibreroLibrero(librero);
    }

    @Override
    public Page<Libro> findByTituloOrAutorOrEditorial(String term, Pageable pageable) {
        return libroDao.findByTituloOrAutorOrEditorial(term, pageable);
    }

}