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
import edu.pe.idat.bibliotecarikkazo.model.Autor;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListAutorAdapter extends RecyclerView.Adapter<ListAutorAdapter.ViewHolder>{

    private List<Autor> autores;

    public ListAutorAdapter (List<Autor> autores){
        this.autores = autores;
    }

    @NonNull
    @NotNull
    @Override
    public ListAutorAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor, parent, false);
        return new ListAutorAdapter.ViewHolder(view);
    }

    //Pa llenar los textView :v
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListAutorAdapter.ViewHolder holder, int position) {
        Autor a = autores.get(position);
        holder.nombreTextView.setText(a.getNombreAutor() + " " + a.getApellido());
        Glide.with(holder.itemView)
                .load(a.getUrlFoto())
                .into(holder.portadaImageView);
    }

    @Override
    public int getItemCount() {
        return autores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView portadaImageView;
        private TextView nombreTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            portadaImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreAutorTextView);
        }
    }
}
