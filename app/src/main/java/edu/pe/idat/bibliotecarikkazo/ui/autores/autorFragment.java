package edu.pe.idat.bibliotecarikkazo.ui.autores;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.adapter.ListAutorAdapter;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Autor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

public class autorFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAutorAdapter listAutorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
        return inflater.inflate(R.layout.fragment_autor, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        getAutores();
    }

    protected void getAutores(){
        Call<List<Autor>> autoresResponse = ApiClient.autorService().findAll();
        autoresResponse.enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if (response.isSuccessful()) {
                    listAutorAdapter = new ListAutorAdapter(response.body());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                    recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
                    recyclerView.setAdapter(listAutorAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                } else Log.e("AUTORES", " onError: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                Log.e("LIBROS", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}