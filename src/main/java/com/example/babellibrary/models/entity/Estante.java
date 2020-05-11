package com.example.babellibrary.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estantes")
public class Estante implements Serializable {

    private static final long serialVersionUID = 7054270440601747437L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String estante;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "librero_id")
    private Librero librero;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstante() {
        return this.estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public Librero getLibrero() {
        return this.librero;
    }

    public void setLibrero(Librero librero) {
        this.librero = librero;
    }

}