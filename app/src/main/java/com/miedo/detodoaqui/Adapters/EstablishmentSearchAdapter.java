package com.miedo.detodoaqui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.miedo.detodoaqui.Data.EstablishmentSearch;
import com.miedo.detodoaqui.Data.EstablishmentUser;
import com.miedo.detodoaqui.EstablishmentDetailsActivity;
import com.miedo.detodoaqui.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EstablishmentSearchAdapter extends RecyclerView.Adapter<EstablishmentSearchAdapter.ViewHolder> {

    List<EstablishmentSearch> establishments;
    Context context;

    public EstablishmentSearchAdapter(List<EstablishmentSearch> establishments, Context context) {
        this.establishments = establishments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_establishment_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EstablishmentSearch selected = establishments.get(position);

        holder.name.setText(selected.getName());
        holder.address.setText(selected.getAddress());
        Picasso.get().load(selected.getUrlImage()).into(holder.photo);
        holder.rating.setRating(selected.getRating());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lleva a la vista de establecimientos
                Intent intent = new Intent(context, EstablishmentDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return establishments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView address;
        public ImageView photo;
        public AppCompatRatingBar rating;
        public View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = view.findViewById(R.id.nomEstTextView);
            address = view.findViewById(R.id.dirEstTextView);
            photo = view.findViewById(R.id.fotEstImageView);
            rating = view.findViewById(R.id.resEstRatingBar);
        }

    }
}
