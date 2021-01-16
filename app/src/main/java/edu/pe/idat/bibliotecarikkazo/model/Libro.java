package edu.pe.idat.bibliotecarikkazo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Libro {

    private Long idLibro;

    private String nombreLibro;

    private String descripcion;

    private String urlPortada;

    private String isbn;

    private Date fechaPublicacion;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    private Double precio;

    private boolean estado;

    private Autor autor;

    private Editorial editorial;

    private Categoria categoria;

    private Idioma idioma;

}
