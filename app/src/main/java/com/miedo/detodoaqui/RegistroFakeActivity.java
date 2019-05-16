package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.stepstone.stepper.StepperLayout;

public class RegistroFakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_fake);


        StepperLayout mStepperLayout = (StepperLayout) findViewById(R.id.stepper);
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));

    }
}
