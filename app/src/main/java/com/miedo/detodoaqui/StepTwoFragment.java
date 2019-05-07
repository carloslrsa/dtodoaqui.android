package com.miedo.detodoaqui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;


public class StepTwoFragment extends Fragment {

    private static final String TAG = StepTwoFragment.class.getSimpleName();

    // TODO : implementar ViewModel para extracción y filtro de las opciones
    final String[] countryValues = {"Perú", "Ecuador", "Colombia", "Venezuela", "Argentina", "Chile", "Uruguay", "Bolivia"};
    final String[] cityValues = {"Lima", "Ica", "Arequipa", "Trujillo", "Moquegua"};
    final String[] districtValues = {"La Molina", "Breaña", "Lince", "Comas", "Callao", "Jesús María", "Pueblo Libre", "Carabayllo"};


    private TextView tv_ciudad;
    private TextView tv_pais;
    private TextView tv_distrito;

    private TextView hint_ciudad, hint_pais, hint_distrito;


    // Cosntructor necesario
    public StepTwoFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // obtener el viewmodel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step2, container, false);

        // Seteamos las vistas
        tv_ciudad = view.findViewById(R.id.tv_ciudad);
        tv_distrito = view.findViewById(R.id.tv_distrito);
        tv_pais = view.findViewById(R.id.tv_pais);

        // Seteamos los hint
        hint_pais = view.findViewById(R.id.hint_country);
        hint_ciudad = view.findViewById(R.id.hint_city);
        hint_distrito = view.findViewById(R.id.hint_district);

        tv_pais.setOnClickListener(new View.OnClickListener() {
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    StepTwoFragment.this.getContext(), android.R.layout.simple_spinner_dropdown_item, countryValues);

            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(StepTwoFragment.this.getContext())
                        .setTitle("País")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                refreshCountry(which);

                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });

        tv_ciudad.setOnClickListener(new View.OnClickListener() {
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    StepTwoFragment.this.getContext(), android.R.layout.simple_spinner_dropdown_item, cityValues);

            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(StepTwoFragment.this.getContext())
                        .setTitle("Ciudad")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                refreshCity(which);

                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });


        tv_distrito.setOnClickListener(new View.OnClickListener() {
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    StepTwoFragment.this.getContext(), android.R.layout.simple_spinner_dropdown_item, districtValues);

            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(StepTwoFragment.this.getContext())
                        .setTitle("Distrito")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                refrestDistrict(which);

                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });


        return view;

    }


    void refreshCountry(int index) {
        if (index > -1 && index < countryValues.length) {
            if (hint_pais.getVisibility() == View.INVISIBLE) {
                hint_pais.setVisibility(View.VISIBLE);
                tv_pais.setPadding(0, 20, 0, 0);
            }
            tv_pais.setText(countryValues[index]);
        }
    }

    void refreshCity(int index) {
        if (index > -1 && index < cityValues.length) {
            if (hint_ciudad.getVisibility() == View.INVISIBLE) {
                hint_ciudad.setVisibility(View.VISIBLE);
                tv_ciudad.setPadding(0, 20, 0, 0);
            }
            tv_ciudad.setText(cityValues[index]);
        }
    }

    void refrestDistrict(int index) {
        if (index > -1 && index < districtValues.length) {
            if (hint_distrito.getVisibility() == View.INVISIBLE) {
                hint_distrito.setVisibility(View.VISIBLE);
                tv_distrito.setPadding(0, 20, 0, 0);
            }
            tv_distrito.setText(districtValues[index]);
        }
    }


}
