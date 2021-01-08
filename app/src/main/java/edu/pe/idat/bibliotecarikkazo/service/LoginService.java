package edu.pe.idat.bibliotecarikkazo.service;

import edu.pe.idat.bibliotecarikkazo.model.request.LoginRequest;
import edu.pe.idat.bibliotecarikkazo.model.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/biblioteca/api/auth")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
