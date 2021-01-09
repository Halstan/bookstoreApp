package edu.pe.idat.bibliotecarikkazo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import edu.pe.idat.bibliotecarikkazo.R;
import edu.pe.idat.bibliotecarikkazo.model.Libro;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListLibroAdapter extends RecyclerView.Adapter<ListLibroAdapter.ViewHolder>{

    private List<Libro> libros;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView portadaImageView;
        private TextView nombreTextView;
        private TextView autorTextView;
        private TextView estadoTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            portadaImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            autorTextView = itemView.findViewById(R.id.autorTextView);
            estadoTextView = itemView.findViewById(R.id.estadoTextView);

        }
    }

    public ListLibroAdapter(List<Libro> libros){
        this.libros = libros;
    }

    @NonNull
    @NotNull
    @Override
    public ListLibroAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListLibroAdapter.ViewHolder holder, int position) {
        Libro p = libros.get(position);
        holder.nombreTextView.setText(p.getNombreLibro());
        holder.autorTextView.setText(p.getAutor().getNombreAutor() + " " + p.getAutor().getApellido());
        if (p.isEstado()){
            holder.estadoTextView.setText("DISPONIBLE");
        } else {
            holder.estadoTextView.setText("RESERVADO");
        }
        Glide.with(holder.itemView)
                .load(p.getUrlPortada())
                .into(holder.portadaImageView);
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }
}
