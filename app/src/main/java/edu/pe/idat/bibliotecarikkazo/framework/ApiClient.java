package edu.pe.idat.bibliotecarikkazo.framework;

import edu.pe.idat.bibliotecarikkazo.service.AlquilerService;
import edu.pe.idat.bibliotecarikkazo.service.LibroService;
import edu.pe.idat.bibliotecarikkazo.service.LoginService;
import edu.pe.idat.bibliotecarikkazo.service.UsuarioService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getretrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.62:7070")
                .client(okHttpClient)
                .build();
    }

    public static LoginService loginService(){

        return getretrofit().create(LoginService.class);
    }

    public static LibroService libroService(){

        return getretrofit().create(LibroService.class);
    }

    public static AlquilerService alquilerService(){

        return getretrofit().create(AlquilerService.class);
    }

    public static UsuarioService usuarioService(){

        return getretrofit().create(UsuarioService.class);
    }

}
