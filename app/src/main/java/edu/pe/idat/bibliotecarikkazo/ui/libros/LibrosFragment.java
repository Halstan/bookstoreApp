package edu.pe.idat.bibliotecarikkazo.ui.libros;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.adapter.ListLibroAdapter;
import edu.pe.idat.bibliotecarikkazo.framework.ApiClient;
import edu.pe.idat.bibliotecarikkazo.model.Libro;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

public class LibrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListLibroAdapter listLibroAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        getLibros();
    }

    protected void getLibros(){
        Call<List<Libro>> libroResponse = ApiClient.libroService().findAll();
        libroResponse.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (response.isSuccessful()){
                    listLibroAdapter = new ListLibroAdapter(response.body());
                    //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
                    recyclerView.setAdapter(listLibroAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                } else Log.e("LIBROS", " onResponse: " + response.errorBody());

            }

            @Override
            public void onFailure(@NotNull Call<List<Libro>> call, @NotNull Throwable t) {
                Log.e("LIBROS", "onFailure: " + t.getMessage());
            }
        });
    }

}