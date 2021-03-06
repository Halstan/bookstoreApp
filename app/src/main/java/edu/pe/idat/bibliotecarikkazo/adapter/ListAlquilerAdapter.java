package edu.pe.idat.bibliotecarikkazo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListAlquilerAdapter extends RecyclerView.Adapter<ListAlquilerAdapter.ViewHolder>{

    private List<Alquiler> alquileres;

    public ListAlquilerAdapter(List<Alquiler> alquileres){
        this.alquileres = alquileres;
    }

    @NonNull
    @NotNull
    @Override
    public ListAlquilerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alquiler, parent, false);
        return new ListAlquilerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListAlquilerAdapter.ViewHolder holder, int position) {
        Alquiler alquiler = alquileres.get(position);
        holder.nombreLibroTextView.setText(alquiler.getLibro().getNombreLibro());
        holder.precioTextView.setText("S/." + alquiler.getPrecio().toString());
        if (alquiler.isEstado()){
            holder.estadoTextView.setText("Alquilando");
        } else {
            holder.estadoTextView.setText("Devuelto");
        }
        Glide.with(holder.itemView)
                .load(alquiler.getLibro().getPortada())
                .into(holder.portadaLibroImageView);
    }

    @Override
    public int getItemCount() {
        return alquileres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView portadaLibroImageView;
        private TextView nombreLibroTextView;
        private TextView precioTextView;
        private TextView estadoTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            portadaLibroImageView = itemView.findViewById(R.id.fotoLibroImageView);
            nombreLibroTextView = itemView.findViewById(R.id.nombreLibroTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            estadoTextView = itemView.findViewById(R.id.estadoTextView);

        }
    }
}
