package com.miedo.detodoaqui.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.miedo.detodoaqui.R;
import com.miedo.detodoaqui.StepFourFragment;
import com.miedo.detodoaqui.StepOneFragment;
import com.miedo.detodoaqui.StepThreeFragment;
import com.miedo.detodoaqui.StepTwoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepperAdapter extends AbstractFragmentStepAdapter {

    final Step[] steps = {

            new StepOneFragment(),
            new StepTwoFragment(),
            new StepThreeFragment(),
            new StepFourFragment()};

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        return steps[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
