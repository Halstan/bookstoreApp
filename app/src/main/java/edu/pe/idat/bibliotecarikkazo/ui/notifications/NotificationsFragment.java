package edu.pe.idat.bibliotecarikkazo.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private TextView textView;
    private EditText etNombre, etApellido, etUsername, etCorreo, etPassword, etConfirmPassword;
    private Spinner cboSexo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        init(view);

        return view;
    }

    void init(View view){
        textView = view.findViewById(R.id.lblUsuario);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etUsername = view.findViewById(R.id.etUsername);
        etCorreo = view.findViewById(R.id.etCorreo);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        cboSexo = view.findViewById(R.id.spSexo);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("credenciales", 0);
        String username = preferences.getString("username", "");
        String token = preferences.getString("token", "");
        getUsuarioByUsername(username, "Bearer " + token);
        super.onViewCreated(view, savedInstanceState);
    }

    private void getUsuarioByUsername(String username, String token){
        Call<Usuario> usuarioResponse = ApiClient.usuarioService().findByUsername(username, token);
        usuarioResponse.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Usuario usuario = response.body();
                    assert usuario != null;
                    textView.setText("Perfil de: " + usuario.getUsername());
                    etNombre.setText(usuario.getNombre());
                    etApellido.setText(usuario.getApellido());
                    etUsername.setText(usuario.getUsername());
                    etCorreo.setText(usuario.getCorreo());
                    if (usuario.getSexo().equals("Masculino")){
                        cboSexo.setSelection(1);
                    } else if (usuario.getSexo().equals("Femenino")) {
                        cboSexo.setSelection(2);
                    } else cboSexo.setSelection(0);


                } else Log.e("USUARIO", " onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("USUARIO", "onFailure: " + t.getMessage());
            }
        });
    }

    private void updatePerfil(String token){
        Usuario usuario1 = new Usuario();
        usuario1.setNombre(etNombre.getText().toString());
        usuario1.setApellido(etApellido.getText().toString());
        usuario1.setUsername(etUsername.getText().toString());
        usuario1.setCorreo(etCorreo.getText().toString());
        usuario1.setContrasenha(etPassword.getText().toString());
        usuario1.setAsegurarContrasenha(etConfirmPassword.getText().toString());
        usuario1.setSexo(cboSexo.getSelectedItem().toString());

        Call<Usuario> usuarioCall = ApiClient.usuarioService().updatePerfil(usuario1, token);
        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){

                } Log.e("USUARIO", " onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }

}