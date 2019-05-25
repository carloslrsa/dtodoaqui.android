package com.miedo.detodoaqui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.miedo.detodoaqui.Data.Local.SessionManager;
import com.miedo.detodoaqui.Data.Remote.CesarFakeAPI;
import com.miedo.detodoaqui.Data.Remote.ServiceGenerator;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("location", finalJSONRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Creamos el objeto RequestBody con el jsonRequest
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest.toString());

        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);

        Call<ResponseBody> call = api.postEstablishment(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(RegisterEstablishmentActivity.this, "Establecimiento registrado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterEstablishmentActivity.this, "Error", Toast.LENGTH_LONG).show();

            }
        });


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
                    finalJSONRequest.put("slug", bundle.getString("nombre", "default slug"));


                    break;
                case STEP_TWO:


                    finalJSONRequest.put("district", bundle.getString("district"));
                    finalJSONRequest.put("latitude", bundle.getDouble("latitude"));
                    finalJSONRequest.put("longitude", bundle.getDouble("longitude"));
                    finalJSONRequest.put("address", bundle.getString("address"));

                    break;

                case STEP_THREE:

                    finalJSONRequest.put("image_name", "gaa");
                    finalJSONRequest.put("is_verified", false);
                    finalJSONRequest.put("created", "2019-10-29T20:20:20Z");
                    finalJSONRequest.put("modified", "2019-10-29T20:20:20Z");
                    finalJSONRequest.put("inserted_at", "2019-10-29T20:20:20Z");
                    finalJSONRequest.put("updated_at", "2019-10-29T20:20:20Z");
                    finalJSONRequest.put("user_id", SessionManager.getInstance().getCurrentSession().getId());
                    break;
                case STEP_FOUR:
                    break;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
