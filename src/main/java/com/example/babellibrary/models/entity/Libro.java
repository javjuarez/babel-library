package com.example.babellibrary.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

    private static final long serialVersionUID = 4256948685585754804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String autor;

    @NotEmpty
    private String editorial;

    private String sinopsis;

    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.DATE)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;

    @OneToOne(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Localizacion localizacion;

    @Column(length = 100)
    private String portada = "";

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getSinopsis() {
        return this.sinopsis;
    }

    public void setsinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Date getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Localizacion getLocalizacion() {
        return this.localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public String getPortada() {
        return this.portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", autor='" + getAutor() + "'" +
            ", editorial='" + getEditorial() + "'" +
            ", sinopsis='" + getSinopsis() + "'" +
            ", fechaPublicacion='" + getFechaPublicacion() + "'" +
            ", localizacion='" + getLocalizacion() + "'" +
            ", portada='" + getPortada() + "'" +
            "}";
    }


}