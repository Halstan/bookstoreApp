package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.response.LibroResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LibroService {

    @GET("/biblioteca/api/libros")
    Call<LibroResponse> findAll();

}
