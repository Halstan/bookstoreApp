package edu.pe.idat.bibliotecarikkazo.ui.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("credenciales", 0);
        String username = preferences.getString("username", "");
        String token = preferences.getString("token", "");
        getAlquileresByUsername(username, "Bearer " + token);
        super.onViewCreated(view, savedInstanceState);

    }

    private void getAlquileresByUsername(String username, String token){
        Call<List<Alquiler>> alquilerResponse = ApiClient.alquilerService().findByUsername(username, token);
        alquilerResponse.enqueue(new Callback<List<Alquiler>>() {
            @Override
            public void onResponse(Call<List<Alquiler>> call, Response<List<Alquiler>> response) {
                if (response.isSuccessful()){
                    List<Alquiler> alquilers = response.body();
                    System.out.println(alquilers);
                } else Log.e("ALQUILERES", " onResponse: " + response.errorBody());

            }

            @Override
            public void onFailure(Call<List<Alquiler>> call, Throwable t) {
                Log.e("ALQUILERES", "onFailure: " + t.getMessage());
            }
        });
    }

}