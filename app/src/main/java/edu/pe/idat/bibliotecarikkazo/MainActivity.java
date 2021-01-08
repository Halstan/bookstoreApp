package edu.pe.idat.bibliotecarikkazo;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.pe.idat.bibliotecarikkazo.model.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Libro;
import edu.pe.idat.bibliotecarikkazo.model.response.LibroResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        getAllLibros();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void getAllLibros(){

        Call<LibroResponse> libroResponse = ApiClient.libroService().findAll();
        libroResponse.enqueue(new Callback<LibroResponse>() {
            @Override
            public void onResponse(Call<LibroResponse> call, Response<LibroResponse> response) {
                if (response.isSuccessful()){
                    LibroResponse libroResponse1 = response.body();
                    assert libroResponse1 != null;
                    ArrayList<Libro> libros = libroResponse1.getLibros();
                }
            }

            @Override
            public void onFailure(Call<LibroResponse> call, Throwable t) {

            }
        });

    }

}