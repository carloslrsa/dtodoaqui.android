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

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEstablishmentActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    StepperLayout mStepperLayout;

    JSONObject finalJSONRequest;

    public static final int STEP_ONE = 1;
    public static final int STEP_TWO = 2;
    public static final int STEP_THREE = 3;
    public static final int STEP_FOUR = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_establishment);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepper);
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);

        finalJSONRequest = new JSONObject();


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

    public void loadData(Bundle bundle, int step) {

        try {

            switch (step) {

                case STEP_ONE:

                    finalJSONRequest.put("name", bundle.getString("nombre", "default name"));

                    break;
                case STEP_TWO:
                    break;

                case STEP_THREE:


                    break;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
