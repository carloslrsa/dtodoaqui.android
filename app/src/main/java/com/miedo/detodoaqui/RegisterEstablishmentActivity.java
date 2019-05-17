package com.miedo.detodoaqui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class RegisterEstablishmentActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_establishment);
        
        mStepperLayout = (StepperLayout) findViewById(R.id.stepper);
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);

    }

    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();


        // Hacer la peticion a la api
    }

    @Override
    public void onError(VerificationError verificationError) {

        //Toast.makeText(this, "onError!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {

        //Toast.makeText(this, "onStepSelected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {

        //Toast.makeText(this, "onReturn!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Salir del registro")
                .setMessage("¿Seguro que quieres salir?")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Quedarse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
}
