package com.example.babellibrary.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localizaciones")
public class Localizacion implements Serializable {

    private static final long serialVersionUID = 5512533313449214187L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String sala;

    @Column(length = 4)
    private String librero;

    @Column(length = 4)    
    private String estante;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSala() {
        return this.sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getLibrero() {
        return this.librero;
    }

    public void setLibrero(String librero) {
        this.librero = librero;
    }

    public String getEstante() {
        return this.estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public Libro getLibro() {
        return this.libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", sala='" + getSala() + "'" +
            ", librero='" + getLibrero() + "'" +
            ", estante='" + getEstante() + "'" +
            ", libro='" + getLibro() + "'" +
            "}";
    }

}