package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.Libro;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface LibroService {

    @GET("/biblioteca/api/libros")
    Call<List<Libro>> findAll();

}
