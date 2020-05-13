package com.santosglaiton.cursomc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CidadeDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoDomain estado;

    public CidadeDomain() {
    }

    public CidadeDomain(Integer id, String nome, EstadoDomain estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoDomain getEstado() {
        return estado;
    }

    public void setEstado(EstadoDomain estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CidadeDomain that = (CidadeDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
