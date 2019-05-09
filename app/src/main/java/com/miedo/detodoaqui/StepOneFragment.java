package com.miedo.detodoaqui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.miedo.detodoaqui.Utils.MultiSpinner;

import java.util.Arrays;

public class StepOneFragment extends Fragment implements MultiSpinner.MultiSpinnerListener {

    private static final String TAG = StepOneFragment.class.getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // obtener el viewmodel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step1, container, false);


        MultiSpinner multiSpinner = (MultiSpinner) view.findViewById(R.id.spinner_services);
        multiSpinner.setItems(
                Arrays.asList("Metalero", "Emo jarcor", "Otaco(agg)", "GAAAA"),
                "Selecciona una o más categorías",
                "Categorías", this);

        view.findViewById(R.id.nextButton).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        );

        return view;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

        Log.i(TAG, "Resultado");
        Log.i(TAG, Arrays.toString(selected));

    }
}
