package edu.pe.idat.bibliotecarikkazo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import edu.pe.idat.bibliotecarikkazo.model.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void getAlquileresByUsername(String username, String token){
        Call<List<Alquiler>> alquilerResponse = ApiClient.alquilerService().findByUsername(username, token);
        alquilerResponse.enqueue(new Callback<List<Alquiler>>() {
            @Override
            public void onResponse(Call<List<Alquiler>> call, Response<List<Alquiler>> response) {

            }

            @Override
            public void onFailure(Call<List<Alquiler>> call, Throwable t) {

            }
        });
    }

}