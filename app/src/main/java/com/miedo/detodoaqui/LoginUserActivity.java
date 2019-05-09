package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miedo.detodoaqui.Data.Remote.CesarFakeAPI;
import com.miedo.detodoaqui.Data.Remote.ServiceGenerator;
import com.miedo.detodoaqui.Utils.JWTUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserActivity extends AppCompatActivity {

    // Log TAG
    private static final String TAG = LoginUserActivity.class.getSimpleName();

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        et_username = findViewById(R.id.usernameUserLoginET);
        et_password = findViewById(R.id.passwordUserLoginET);

        Button bt_login = findViewById(R.id.loginUserButton);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtenemos el cuerpo del body para la peticion post en forma de string
                String jsonRequest = fetchStringBody();

                // Creamos la instancia de la api
                CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);

                // Creamos el objeto RequestBody con el jsonRequest
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest);

                // Realizamos la peticion asincrona
                Call<ResponseBody> call = api.loginUsuario(body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                            String rspta = fetchResponse(response.body());
                            Log.i(TAG, "Respuesta : "+rspta);

                            Toast.makeText(LoginUserActivity.this, "Funciono el login GAAAAAAA", Toast.LENGTH_LONG).show();


                        } else {

                            Toast.makeText(LoginUserActivity.this, "Error response code:" + response.code(), Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(LoginUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }

    private String fetchStringBody() {
        JSONObject request = new JSONObject();

        JSONObject user = new JSONObject();

        String retorno = null;

        try {

            user.put("email", et_username.getText().toString());
            user.put("password", et_password.getText().toString());
            user.put("password_confirmation", et_password.getText().toString());

            request.put("user", user);

            Log.i(TAG, request.toString(4));

            retorno = request.toString();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return retorno;

    }


    private String fetchResponse(ResponseBody response) {

        String retorno = null;
        try {
            String tokenResponse = new JSONObject(response.string()).getString("jwt");
            retorno = JWTUtils.getJson(tokenResponse);

        } catch (JSONException e) {
            Log.e(TAG , e.getMessage());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG , e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;

    }
}
