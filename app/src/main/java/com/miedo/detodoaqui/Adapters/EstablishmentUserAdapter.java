package com.miedo.detodoaqui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.miedo.detodoaqui.Data.EstablishmentUser;
import com.miedo.detodoaqui.R;

import java.util.List;


public class EstablishmentUserAdapter extends RecyclerView.Adapter<EstablishmentUserAdapter.ViewHolder> {

    private final List<EstablishmentUser> mValues;
    private final Context mContext;

    public EstablishmentUserAdapter(List<EstablishmentUser> items, Context context) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_establishment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tv_title.setText(mValues.get(position).getTitle());
        holder.tv_number_reviews.setText(String.valueOf(mValues.get(position).getReviews()));
        holder.rb_rating.setRating( mValues.get(position).getRating());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public final TextView tv_title;
        public final TextView tv_number_reviews;
        public final ImageView iv_picture;
        public final AppCompatRatingBar rb_rating;
        public EstablishmentUser mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_title = view.findViewById(R.id.tv_title_establishment);
            tv_number_reviews = view.findViewById(R.id.tv_number_reviews);
            iv_picture = view.findViewById(R.id.iv_entablishment_picture);
            rb_rating = view.findViewById(R.id.rb_rating);
        }

    }
}
