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
import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


public class StepTwoFragment extends Fragment implements Step, StepperAdapter.StepDataListener {

    private static final String TAG = StepTwoFragment.class.getSimpleName();

    // variables para la identificación de erores
    private static final String DIRECCION_ERROR = "1";

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
        et_direccion = view.findViewById(R.id.et_direccion);


        return view;

    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        VerificationError ve = null;

        et_direccion.setError(null);

        if (et_direccion.getText().toString().isEmpty()) {
            ve = new VerificationError(DIRECCION_ERROR);
        }
        return ve;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

        if (error.getErrorMessage().equals(DIRECCION_ERROR)) {
            et_direccion.setError("Debes ingresar la dirección");
        }

    }

    @Override
    public Bundle getData() {
        Bundle bundle = new Bundle();
        bundle.putString("direccion", et_direccion.getText().toString().trim());
        return bundle;
    }
}
