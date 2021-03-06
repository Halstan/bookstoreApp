package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.Autor;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface AutorService {

    @GET("/biblioteca/api/autores")
    Call<List<Autor>> findAll();

}
