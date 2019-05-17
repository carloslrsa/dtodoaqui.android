package com.miedo.detodoaqui.Utils;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.transition.TransitionManager;

import com.miedo.detodoaqui.R;

public class StateViewUtils {


    public static void setLoading(View view) {

        view.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        view.findViewById(R.id.message_icon).setVisibility(View.GONE);
        view.findViewById(R.id.message_title).setVisibility(View.GONE);
        view.findViewById(R.id.message_description).setVisibility(View.GONE);
    }

    public static void setNotFoundEstablishmentState(View view, View anotherView, int resIcon, int resTitle, int resDescription) {

        view.findViewById(R.id.progress_bar).setVisibility(View.GONE);
        ImageView icono = view.findViewById(R.id.message_icon);
        icono.setImageResource(resIcon);
        TextView titulo = view.findViewById(R.id.message_title);
        titulo.setText(resTitle);
        TextView descripcion = view.findViewById(R.id.message_description);
        descripcion.setText(resDescription);

        icono.setVisibility(View.VISIBLE);
        titulo.setVisibility(View.VISIBLE);
        descripcion.setVisibility(View.VISIBLE);

    }

}
