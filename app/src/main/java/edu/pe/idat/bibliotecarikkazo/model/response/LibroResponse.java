package edu.pe.idat.bibliotecarikkazo.model.response;

import edu.pe.idat.bibliotecarikkazo.model.Libro;

import java.util.ArrayList;

public class LibroResponse {

    private ArrayList<Libro> libros;

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
}
