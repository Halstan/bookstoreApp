package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface AlquilerService {

    @GET("/biblioteca/api/alquileres/usuario/{username}")
    Call<List<Alquiler>> findByUsername(@Path("username") String username, @Header("Authorization") String lang);

}
