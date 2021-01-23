package edu.pe.idat.bibliotecarikkazo.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.RegisterActivity;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Rol;
import edu.pe.idat.bibliotecarikkazo.model.Usuario;
import edu.pe.idat.bibliotecarikkazo.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class NotificationsFragment extends Fragment {

    private TextView textView;
    private EditText etNombre, etApellido, etUsername, etCorreo, etPassword, etConfirmPassword;
    private Spinner cboSexo;
    private Button btnActualizar;
    private Set<Rol> rolesSet;
    private Long idUsuario;

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
        btnActualizar = view.findViewById(R.id.btnActualizar);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("credenciales", 0);
        String username = preferences.getString("username", "");
        String token = preferences.getString("token", "");
        getUsuarioByUsername(username, "Bearer " + token);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerfil("Bearer " + token);
            }
        });

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

                    idUsuario = usuario.getIdUsuario();
                    rolesSet = usuario.getRoles();

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
        usuario1.setIdUsuario(idUsuario);
        usuario1.setRoles(rolesSet);
        Utils.setUsuario(usuario1, etNombre, etApellido, etUsername, etCorreo
                , etPassword, etConfirmPassword, cboSexo);

        Call<Usuario> usuarioCall = ApiClient.usuarioService().updatePerfil(usuario1, token);
        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(), "Usuario actualizado", Toast.LENGTH_SHORT).show();
                } Log.e("USUARIO", " onResponse: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("USUARIO", "onFailure: " + t.getMessage());
            }
        });

    }

}