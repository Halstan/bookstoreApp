package edu.pe.idat.bibliotecarikkazo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.auth0.android.jwt.JWT;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Usuario;
import edu.pe.idat.bibliotecarikkazo.model.request.LoginRequest;
import edu.pe.idat.bibliotecarikkazo.model.response.LoginResponse;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button webView;
    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.etUsuario);
        txtPassword = findViewById(R.id.etPassword);
        webView = findViewById(R.id.btnCrearcuenta);
        btnLogin = findViewById(R.id.btnIngresar);
        checkBox = findViewById(R.id.confirmPassword);
        webView = findViewById(R.id.btnCrearcuenta);
        btnLogin = findViewById(R.id.btnIngresar);

        cargarPreferencias();

        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (TextUtils.isEmpty(txtUsername.getText().toString()) || TextUtils.isEmpty(txtPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                } else {
                    LoginActivity.this.login();
                }
            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(txtUsername.getText().toString());
        loginRequest.setPassword(txtPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.loginService().login(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();

                        assert loginResponse != null;

                        try {
                            JWT jwt = new JWT(loginResponse.getJwt());
                            guardarPreferencias(jwt, loginResponse.getJwt());

                        } catch(Exception e){
                            e.printStackTrace();
                        }

                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }, 700);

                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarPreferencias(JWT jwt, String token){
        SharedPreferences preferences = getSharedPreferences("credenciales", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", jwt.getSubject());
        editor.putString("token", token);
        if ( checkBox.isChecked() ){
            editor.putString("password", txtPassword.getText().toString());
        }
        // Apply the edits!
        editor.apply();
    }

    private void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("credenciales", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        txtUsername.setText(username);
        txtPassword.setText(password);
        if (password.length() > 0){
            checkBox.setChecked(true);
        }
    }

}