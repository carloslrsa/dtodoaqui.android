package com.miedo.detodoaqui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.miedo.detodoaqui.Utils.MultiSpinner;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.Arrays;
import java.util.List;

public class StepOneFragment extends Fragment implements MultiSpinner.MultiSpinnerListener, Step {

    private static final String TAG = StepOneFragment.class.getSimpleName();

    // variables para identificar el tipo de error
    private static final String NOMBRE_VACIO_ERROR = "1";
    private static final String CATEGORIAS_ERROR = "2";

    // lista de categorías para seleccionar
    List<String> categorias = Arrays.asList("Restaurant", "Karaoke", "Bar", "Florería", "Bodega", "Cibercafé");

    // Array de booleans para guardar las opciones seleccionadas
    boolean[] seleccionados = new boolean[categorias.size()];


    // Views para actualizar.
    private FlexboxLayout flexboxLayout;
    private TextInputEditText et_nombre;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step1, container, false);

        // set flexbox
        flexboxLayout = view.findViewById(R.id.flex_box);

        // set edit text
        et_nombre = view.findViewById(R.id.et_nombre);

        // seteamos y confguramos el multispinner
        MultiSpinner multiSpinner = (MultiSpinner) view.findViewById(R.id.spinner_services);
        multiSpinner.setItems(
                categorias,
                "Selecciona una o más categorías",
                "Categorías", this);

        return view;
    }

    // actualizar la interfaz de la seleccion de categorías
    public void updateItemsSelected() {
        flexboxLayout.removeAllViews();
        for (int i = 0; i < seleccionados.length; i++) {
            if (seleccionados[i]) {

                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.label_category, null);
                textView.setText(categorias.get(i));

                flexboxLayout.addView(textView);
                Log.i(TAG, "Seleccionado: " + categorias.get(i));

            }
        }
    }


    // metodo para actualizar la lista de opciones seleccionadas y actualizarla
    @Override
    public void onItemsSelected(boolean[] selected) {
        seleccionados = selected;
        updateItemsSelected();
    }


    // metodo del Step para verificar si hay error
    @Nullable
    @Override
    public VerificationError verifyStep() {
        et_nombre.setError(null);

        String nombre = et_nombre.getText().toString();
        VerificationError ve = null;
        if (nombre.isEmpty()) {
            ve = new VerificationError(NOMBRE_VACIO_ERROR);
        } else {
            boolean selected = false;
            for (boolean b : seleccionados) {
                if (b) selected = true;
            }
            if (!selected) {
                ve = new VerificationError(CATEGORIAS_ERROR);
            }
        }

        if (ve == null) {
            updateData();
        }

        return ve;
    }

    @Override
    public void onSelected() {
        Log.i(TAG, "onSelected");
        updateItemsSelected();
    }


    // metodo para actuar despues de hayada la interfaz
    @Override
    public void onError(@NonNull VerificationError error) {
        if (error.getErrorMessage().equals(NOMBRE_VACIO_ERROR)) {
            et_nombre.setError("El nombre no puede estar vacío");
            et_nombre.requestFocus();
        } else if (error.getErrorMessage().equals(CATEGORIAS_ERROR)) {
            Toast.makeText(getContext(), "Selecciona al menos una categoría", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateData() {

        Bundle bundle = new Bundle();

        bundle.putString("nombre", et_nombre.getText().toString().trim());
        //bundle.putBooleanArray("categorias", seleccionados);

        // FAKE
        ((RegisterEstablishmentActivity) getActivity()).loadData(bundle, RegisterEstablishmentActivity.STEP_ONE);


    }
}
