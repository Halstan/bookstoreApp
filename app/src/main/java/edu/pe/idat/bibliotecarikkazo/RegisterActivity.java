package edu.pe.idat.bibliotecarikkazo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Usuario;
import edu.pe.idat.bibliotecarikkazo.model.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextUsername, editTextCorreo,
            editTextContrasenha, editTextConfirmContrasenha;
    private Spinner cboSexoRegistro;
    private Button btnCancelar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasenha = findViewById(R.id.editTextConfirmContrasenha);
        editTextConfirmContrasenha = findViewById(R.id.editTextConfirmContrasenha);
        cboSexoRegistro = findViewById(R.id.cboSexoRegistro);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

    }

    private void register(Usuario usuario){
        Call<Usuario> usuarioResponse = ApiClient.usuarioService().createUsuario(usuario);
        usuarioResponse.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){

                } else {
                    Toast.makeText(RegisterActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("LIBROS", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}
