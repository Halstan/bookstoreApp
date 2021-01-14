package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.Usuario;
import retrofit2.Call;
import retrofit2.http.*;

public interface UsuarioService {

    @GET("/biblioteca/api/usuarios/username/{username}")
    Call<Usuario> findByUsername(@Path("username") String username, @Header("Authorization") String token);

    @PUT("/biblioteca/api/usuarios")
    Call<Usuario> updatePerfil(@Body Usuario usuario, @Header("Authorization") String token);

}
