package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.response.AlquilerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlquilerService {

    @GET("/biblioteca/api/alquileres/usuario/{username}")
    Call<AlquilerResponse> findByUsername(@Path("username") String username);

}
