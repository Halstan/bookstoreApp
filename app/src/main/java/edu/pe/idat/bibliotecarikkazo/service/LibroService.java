package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.Libro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface LibroService {

    @GET("/biblioteca/api/libros")
    Call<List<Libro>> findAll();

    @GET("/biblioteca/api/libros/isbn/{isbn}")
    Call<Libro> findByIsbn(@Path("isbn") String isbn, @Header("Authorization") String token);

}
