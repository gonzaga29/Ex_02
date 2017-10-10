package com.example.n3.ex_02;

import java.io.Serializable;

/**
 * Created by N3 on 27/09/2017.
 */

public class Filmes_Activity implements Serializable {

    private long id;
    private String titulo;
    private Integer disponibilidade;
    private String genero;
    private Integer nota;
    private String censura;
    private Integer audio;
    private byte[] capa;


    public Filmes_Activity(long id,String titulo, Integer disponibilidade, String genero, Integer nota,
                          String censura, Integer audio,byte[] capa) {
        this.titulo = titulo;
        this.disponibilidade = disponibilidade;
        this.genero = genero;
        this.nota = nota;
        this.censura = censura;
        this.audio = audio;
        this.capa = capa;
        this.id = id;
    }

    public Filmes_Activity(){}

    public Filmes_Activity(String titulo, Integer disponibilidade, String genero, Integer nota,
                           String censura, Integer audio,byte[] capa){

        this(0,titulo,disponibilidade,genero,nota,censura,audio,capa);
    }

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

    public Integer getAudio() {
        return audio;
    }

    public void setAudio(Integer  audio) {
        this.audio = audio;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Integer disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}
