package com.miedo.detodoaqui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


public class StepTwoFragment extends Fragment implements Step {

    private static final String TAG = StepTwoFragment.class.getSimpleName();

    // variables para la identificación de erores
    private static final String DIRECCION_ERROR = "1";
    private static final String PAIS_ERROR = "2";
    private static final String CIUDAD_ERROR = "3";
    private static final String DISTRITO_ERROR = "4";

    // variables hardcode de las opciones
    final String[] countryValues = {"Perú", "Ecuador", "Colombia", "Venezuela", "Argentina", "Chile", "Uruguay", "Bolivia"};
    final String[] cityValues = {"Lima", "Ica", "Arequipa", "Trujillo", "Moquegua"};
    final String[] districtValues = {"La Molina", "Breaña", "Lince", "Comas", "Callao", "Jesús María", "Pueblo Libre", "Carabayllo"};

    String[] seleccionados = new String[3];

    private TextView tv_ciudad;
    private TextView tv_pais;
    private TextView tv_distrito;

    private TextInputEditText et_direccion;

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
        et_direccion = view.findViewById(R.id.et_direccion);


        // listeners para desplegar los dialogs de seleccion
        tv_pais.setOnClickListener(getListenerCountries());
        tv_ciudad.setOnClickListener(getListenerCities());
        tv_distrito.setOnClickListener(getListenerDistricts());

        return view;

    }

    private View.OnClickListener getListenerCountries() {
        return new View.OnClickListener() {
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
        };
    }


    private View.OnClickListener getListenerCities() {
        return new View.OnClickListener() {
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
        };
    }

    private View.OnClickListener getListenerDistricts() {
        return new View.OnClickListener() {
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
        };
    }


    void refreshCountry(int index) {
        if (index > -1 && index < countryValues.length) {
            seleccionados[0] = countryValues[index];
            tv_pais.setText(countryValues[index]);
        }
    }

    void refreshCity(int index) {
        if (index > -1 && index < cityValues.length) {
            seleccionados[1] = cityValues[index];
            tv_ciudad.setText(cityValues[index]);
        }
    }

    void refrestDistrict(int index) {
        if (index > -1 && index < districtValues.length) {
            seleccionados[2] = districtValues[index];
            tv_distrito.setText(districtValues[index]);
        }
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        VerificationError ve = null;

        et_direccion.setError(null);
        tv_pais.setError(null);
        tv_ciudad.setError(null);
        tv_distrito.setError(null);

        if (et_direccion.getText().toString().isEmpty()) {
            ve = new VerificationError(DIRECCION_ERROR);
        } else if (seleccionados[0] == null) {
            ve = new VerificationError(PAIS_ERROR);
        } else if (seleccionados[1] == null) {
            ve = new VerificationError(CIUDAD_ERROR);
        } else if (seleccionados[2] == null) {
            ve = new VerificationError(DISTRITO_ERROR);
        }
        return ve;
    }

    @Override
    public void onSelected() {

        if (seleccionados[0] == null) {
            return;
        }

        tv_pais.setText(seleccionados[0]);
        tv_ciudad.setText(seleccionados[1]);
        tv_distrito.setText(seleccionados[2]);

    }

    @Override
    public void onError(@NonNull VerificationError error) {

        if (error.getErrorMessage().equals(DIRECCION_ERROR)) {
            et_direccion.setError("Debes ingresar la dirección");
        } else if (error.getErrorMessage().equals(PAIS_ERROR)) {
            tv_pais.setError("Debes escoger el país");
            Toast.makeText(getContext(), "Debes escoger el país", Toast.LENGTH_SHORT).show();
        } else if (error.getErrorMessage().equals(CIUDAD_ERROR)) {
            tv_ciudad.setError("Debes escoger la ciudad");
            Toast.makeText(getContext(), "Debes escoger la ciudad", Toast.LENGTH_SHORT).show();
        } else if (error.getErrorMessage().equals(DISTRITO_ERROR)) {
            tv_distrito.setError("Debes seleccionar un distrito");
            Toast.makeText(getContext(), "Debes escoger el distrito", Toast.LENGTH_SHORT).show();
        }

    }
}
