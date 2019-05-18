package com.miedo.detodoaqui.Viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.Remote.CesarFakeAPI;
import com.miedo.detodoaqui.Data.Remote.ServiceGenerator;
import com.miedo.detodoaqui.Data.User;
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

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> userLogin = new MutableLiveData<User>();
    private MutableLiveData<User> userRegister = new MutableLiveData<User>();


    public MutableLiveData<User> getUserLogin() {
        return userLogin;
    }

    public MutableLiveData<User> getUserRegister() {
        return userRegister;
    }

    public void Register(String username, String email, String password, String password_confirm){
        // Obtenemos el cuerpo del body para la peticion post en forma de string
        String jsonRequest = fetchStringRegisterBody(username,email,password,password_confirm);
        // Creamos la instancia de la api
        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);
        // Creamos el objeto RequestBody con el jsonRequest
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest);
        // Realizamos la peticion asincrona
        Call<ResponseBody> call = api.RegisterUser(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String rspta = fetchResponse(response.body());
                    userRegister.setValue(new User());
                } else {
                    userRegister.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                userRegister.setValue(null);
            }
        });
    }

    public void Login(String username, String password){
        // Obtenemos el cuerpo del body para la peticion post en forma de string
        String jsonRequest = fetchStringLoginBody(username,password);
        // Creamos la instancia de la api
        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);
        // Creamos el objeto RequestBody con el jsonRequest
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest);
        // Realizamos la peticion asincrona
        Call<ResponseBody> call = api.LoginUser(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String rspta = fetchResponse(response.body());
                    Log.e("Holi",rspta);
                    userLogin.setValue(new User());
                } else {
                    userLogin.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                userLogin.setValue(null);
            }
        });
    }

    private void getProfile(User user){

    }


    private String fetchStringRegisterBody(String username, String email, String password, String password_confirm){
        JSONObject request = new JSONObject();
        JSONObject userJson = new JSONObject();
        String retorno = null;

        try {

            userJson.put("email", email);
            userJson.put("username", username);
            userJson.put("password", password);
            userJson.put("password_confirmation", password_confirm);

            request.put("user",userJson);

            retorno = request.toString();
        } catch (JSONException e) {
        }
        return retorno;
    }

    private String fetchStringLoginBody(String username, String password) {
        JSONObject request = new JSONObject();

        String retorno = null;

        try {

            request.put("username", username);
            request.put("password", password);

            retorno = request.toString();
        } catch (JSONException e) {
        }
        return retorno;
    }


    private String fetchResponse(ResponseBody response) {

        String retorno = null;
        try {
            String tokenResponse = new JSONObject(response.string()).getString("jwt");
            retorno = JWTUtils.getJson(tokenResponse);

        } catch (JSONException e) {
            //Log.e(TAG , e.getMessage());
        } catch (UnsupportedEncodingException e) {
            //Log.e(TAG , e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;

    }


    public LiveData<User> userLogin(){
        return userLogin;
    }
}
