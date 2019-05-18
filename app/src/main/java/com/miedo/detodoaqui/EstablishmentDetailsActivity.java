package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class EstablishmentDetailsActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;

    //TODO cambiar por peticion
    private String[] categorias = {"Internet", "Bodega", "Ratacueva"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_details);

        // set flexbox
        flexboxLayout = findViewById(R.id.flex_box);

        //updateFlexbox();

    }

    // actualizar la interfaz de la seleccion de categorías
    public void updateFlexbox() {
        flexboxLayout.removeAllViews();
        for (int i = 0; i < categorias.length; i++) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.label_category, null);
            textView.setText(categorias[i]);
            flexboxLayout.addView(textView);

        }
    }

}
