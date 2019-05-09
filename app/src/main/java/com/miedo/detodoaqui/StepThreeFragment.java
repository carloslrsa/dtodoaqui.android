package com.miedo.detodoaqui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

public class StepThreeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtener viewmodel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step3, container, false);

        final ImageView iv_fondo = view.findViewById(R.id.iv_fondo);
        final ImageView iv_perfil = view.findViewById(R.id.iv_perfil);

        Picasso.get().load("https://img.elcomercio.pe/files/ec_article_multimedia_gallery/uploads/2017/03/21/58d1ec9d41c8d.jpeg")
                .into(iv_fondo);
        Picasso.get().load("https://pbs.twimg.com/profile_images/793844362698428416/_VXIiSQT.jpg")
                .into(iv_perfil);


        view.findViewById(R.id.nextButton).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        );


        return view;
    }
}
