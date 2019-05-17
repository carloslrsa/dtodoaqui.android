package com.miedo.detodoaqui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.miedo.detodoaqui.Data.EstablishmentUser;
import com.miedo.detodoaqui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class EstablishmentUserAdapter extends RecyclerView.Adapter<EstablishmentUserAdapter.ViewHolder> {

    private List<EstablishmentUser> data;
    private Context mContext;

    public EstablishmentUserAdapter(Context context) {
        data = new ArrayList<>();
        mContext = context;
    }

    // actualiza el recyclerview
    public void setData(List<EstablishmentUser> newData) {
        if (data != null) {
            EstablishmentUserDiffCallback postDiffCallback = new EstablishmentUserDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);

        } else {
            // first initialization
            data = newData;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.cardview_establishment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public final TextView tv_title;
        public final TextView tv_number_reviews;
        public final ImageView iv_picture;
        public final AppCompatRatingBar rb_rating;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_title = view.findViewById(R.id.tv_title_establishment);
            tv_number_reviews = view.findViewById(R.id.tv_number_reviews);
            iv_picture = view.findViewById(R.id.iv_entablishment_picture);
            rb_rating = view.findViewById(R.id.rb_rating);
        }

        void bind(final EstablishmentUser establishmentUser) {
            if (establishmentUser != null) {

                tv_title.setText(establishmentUser.getTitle());
                tv_number_reviews.setText(String.valueOf(establishmentUser.getReviews()));
                rb_rating.setRating(establishmentUser.getRating());
                Picasso.get().load(establishmentUser.getUrlImage()).into(iv_picture);

                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // completar GAAAAAAAAAAAAA
                    }
                });
            }
        }

    }

    /*
     * Clase de utilidad para optimizar la carga de datos en el recycler.
     * */
    class EstablishmentUserDiffCallback extends DiffUtil.Callback {

        private final List<EstablishmentUser> oldItems, newItems;

        EstablishmentUserDiffCallback(List<EstablishmentUser> oldItems, List<EstablishmentUser> newItems) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).getId() == newItems.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}
