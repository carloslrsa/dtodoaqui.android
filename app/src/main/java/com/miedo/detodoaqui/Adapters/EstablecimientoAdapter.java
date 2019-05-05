package com.miedo.detodoaqui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miedo.detodoaqui.R;

public class EstablecimientoAdapter extends RecyclerView.Adapter<EstablecimientoAdapter.EstablecimientoViewHolder>  {

    //List<>

    @NonNull
    @Override
    public EstablecimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.establecimiento_cardview, parent, false);
        return new EstablecimientoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EstablecimientoViewHolder holder, int position) {
        /*studentData data=studentDataList.get(i);
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewHolder.parent.setBackgroundColor(currentColor);
        viewHolder.name.setText(data.name);
        viewHolder.age.setText(String.valueOf(data.age));*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EstablecimientoViewHolder extends RecyclerView.ViewHolder {
        public EstablecimientoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
