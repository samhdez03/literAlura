package com.alura.literAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)  //se debe poner antes del atributo
    private String titulo;
    private List<String> idiomas;
    private Integer numeroDescargas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idiomas = datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();


    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public String toString() {
        return "---------- Libro --------------\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor + '\n' +
                "Idioma: " + idiomas + '\n' +
                "Numero de Descargas: " + numeroDescargas
                + "\n-----------------------------\n";
    }
}


