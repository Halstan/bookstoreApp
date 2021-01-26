package edu.pe.idat.bibliotecarikkazo.ui.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.adapter.ListAlquilerAdapter;
import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAlquilerAdapter listAlquilerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("credenciales", 0);
        String username = preferences.getString("username", "");
        String token = preferences.getString("token", "");
        recyclerView = view.findViewById(R.id.recyclerView2);
        getAlquileresByUsername(username, "Bearer " + token);
        super.onViewCreated(view, savedInstanceState);

    }

    private void getAlquileresByUsername(String username, String token){
        Call<List<Alquiler>> alquilerResponse = ApiClient.alquilerService().findByUsername(username, token);
        alquilerResponse.enqueue(new Callback<List<Alquiler>>() {
            @Override
            public void onResponse(Call<List<Alquiler>> call, Response<List<Alquiler>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Alquiler> alquileres = response.body();
                            listAlquilerAdapter = new ListAlquilerAdapter(alquileres);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
                            recyclerView.setAdapter(listAlquilerAdapter);
                            recyclerView.setLayoutManager(layoutManager);

                    } else {
                        closeFragment(DashboardFragment.this);
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<List<Alquiler>> call, @NotNull Throwable t) {
                Log.e("ALQUILERES", "onFailure: " + t.getMessage());
            }
        });
    }

    void closeFragment(Fragment fragment){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

}