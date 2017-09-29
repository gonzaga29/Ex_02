package com.example.n3.ex_02;

import java.io.Serializable;

/**
 * Created by N3 on 27/09/2017.
 */

public class Flmes_Activity implements Serializable {

    private long id;
    private String titulo;
    private Boolean disponibilidade;
    private String genero;
    private Integer nota;
    private String censura;
    private Boolean audio;
    private String capa;


    public Flmes_Activity(long id,String titulo, Boolean disponibilidade, String genero, Integer nota,
                          String censura, Boolean audio, String capa) {
        this.titulo = titulo;
        this.disponibilidade = disponibilidade;
        this.genero = genero;
        this.nota = nota;
        this.censura = censura;
        this.audio = audio;
        this.capa = capa;
        this.id = id;
    }

    public Flmes_Activity(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getCensura() {
        return censura;
    }

    public void setCensura(String censura) {
        this.censura = censura;
    }

    public Boolean getAudio() {
        return audio;
    }

    public void setAudio(Boolean  audio) {
        this.audio = audio;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}
