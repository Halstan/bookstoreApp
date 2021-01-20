package edu.pe.idat.bibliotecarikkazo;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Libro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNombre, tvAutor, tvEditorial, tvCategoria, tvIdioma, tvPrecio, tvDescripcion;
    private ImageView ivPortada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNombre = findViewById(R.id.tvNombre);
        tvAutor = findViewById(R.id.tvAutor);
        tvEditorial = findViewById(R.id.tvEditorial);
        tvCategoria = findViewById(R.id.tvCategoria);
        tvIdioma = findViewById(R.id.tvIdioma);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        ivPortada = findViewById(R.id.ivPortada);

        String isbn = getIntent().getExtras().getString("isbn");
        String image = getIntent().getExtras().getString("image");

        Glide.with(this)
                .load(image)
                .into(ivPortada);

        SharedPreferences preferences = getSharedPreferences("credenciales", 0);
        String token = preferences.getString("token", "");

        getLibro(isbn, "Bearer " + token);

    }

    private void getLibro(String isbn, String token){
        Call<Libro> libroResponse = ApiClient.libroService().findByIsbn(isbn, token);
        libroResponse.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if (response.isSuccessful()){
                    Libro libro = response.body();
                    assert libro != null;
                    tvNombre.setText(libro.getNombreLibro());
                    tvAutor.setText(libro.getAutor().getNombreAutor() + " " + libro.getAutor().getApellido());
                    tvEditorial.setText(libro.getEditorial().getNombreEditorial());
                    tvCategoria.setText(libro.getCategoria().getNombreCategoria());
                    tvIdioma.setText(libro.getIdioma().getNombreIdioma());
                    if (libro.getPrecio() != null){
                        tvPrecio.setText("S/. " + libro.getPrecio().toString());
                    } else tvPrecio.setText("Gratuito");
                    tvDescripcion.setText(libro.getDescripcion());
                } else Log.e("LIBRO", " onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Throwable: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}