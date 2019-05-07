package com.miedo.detodoaqui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


        MultiSpinner multiSpinner = (MultiSpinner) view.findViewById(R.id.multispinner);
        multiSpinner.setItems(
                Arrays.asList("Metalero", "Emo jarcor", "Otaco(agg)", "GAAAA"),
                "GAAAAAAAAAAA", this);

        return view;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

        Log.i(TAG, "GAAAAAAAAAAAAAAAAAAA");

    }
}
