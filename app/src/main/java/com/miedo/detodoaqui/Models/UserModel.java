package com.miedo.detodoaqui.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.miedo.detodoaqui.Data.Local.SessionManager;
import com.miedo.detodoaqui.Data.Profile;
import com.miedo.detodoaqui.Data.Remote.CesarFakeAPI;
import com.miedo.detodoaqui.Data.Remote.ServiceGenerator;
import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Utils.JWT;
import com.miedo.detodoaqui.Utils.JWTUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel {

    private static MutableLiveData<User> liveUser = new MutableLiveData<User>();

    public MutableLiveData<User> getLiveUser(){
        return liveUser;
    }

    public UserModel(){

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
                    String token = fetchTokenResponse(response.body());
                    String userId = fetchUserIdResponse(token);

                    getProfile(token, new User(userId ,username, password));
                } else {
                    liveUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveUser.setValue(null);
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
                    String token = fetchTokenResponse(response.body());
                    String userId = fetchUserIdResponse(token);

                    getProfile(token, new User(userId ,username, password));
                } else {
                    liveUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveUser.setValue(null);
            }
        });
    }

    public void Logout(){
        //liveUser = new MutableLiveData<>();
        liveUser.setValue(null);
        SessionManager.getInstance().CloseSession();
    }

    private void CreateProfile(User user, Profile profile){
        // Obtenemos el cuerpo del body para la peticion post en forma de string
        String jsonRequest = fetchStringProfileBody(user,profile);
        // Creamos la instancia de la api
        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);
        // Creamos el objeto RequestBody con el jsonRequest
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest);
        // Realizamos la peticion asincrona
        Call<ResponseBody> call = api.CreateProfile(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    user.setProfile(profile);

                    liveUser.setValue(user);
                    SessionManager.getInstance().StartSession(user);
                } else {
                    //Error al crear el perfil
                    liveUser.setValue(user);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveUser.setValue(null);
            }
        });
    }

    public void UpdateProfile(User user, Profile profile){
        if(user.getProfile() == null){
            CreateProfile(user,profile);
        }else{
            // Obtenemos el cuerpo del body para la peticion post en forma de string
            String jsonRequest = fetchStringProfileBody(user,profile);
            // Creamos la instancia de la api
            CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);
            // Creamos el objeto RequestBody con el jsonRequest
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequest);
            // Realizamos la peticion asincrona
            Call<ResponseBody> call = api.CreateProfile(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        user.setProfile(profile);

                        liveUser.setValue(user);
                        SessionManager.getInstance().StartSession(user);
                    } else {
                        //Error al crear el perfil
                        liveUser.setValue(user);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    liveUser.setValue(null);
                }
            });
        }
    }

    private void getProfile(String token, User user){
        // Creamos la instancia de la api
        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);
        // Creamos el header
        String header = "Bearer "+token;
        // Realizamos la peticion asincrona
        Call<ResponseBody> call = api.GetProfile(header);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Profile profile = null;
                if (response.isSuccessful()) {
                    JSONObject jsonResponse = fetchProfileResponse(response.body());
                    if(jsonResponse != null){
                        try {
                            profile = new Profile(jsonResponse.getString("id"),
                                    jsonResponse.getString("first_name"),
                                    jsonResponse.getString("last_name"),
                                    jsonResponse.getString("phone"),
                                    jsonResponse.getString("country"),
                                    jsonResponse.getString("address"),
                                    jsonResponse.getString("description"),
                                    jsonResponse.getString("facebook"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                user.setProfile(profile);
                SessionManager.getInstance().StartSession(user);
                liveUser.setValue(user);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                SessionManager.getInstance().StartSession(user);
                liveUser.setValue(user);
            }
        });
    }

    private String fetchStringProfileBody(User user, Profile profile){
        JSONObject request = new JSONObject();
        JSONObject profileJson = new JSONObject();
        String retorno = null;

        try {
            profileJson.put("address", profile.getAddress());
            profileJson.put("avatar_name", "avatar_name_test");
            profileJson.put("country", profile.getCountry());
            profileJson.put("created", "2019-10-29T20:12:30Z");
            profileJson.put("description", profile.getDescription());
            profileJson.put("facebook", profile.getFacebookUrl());
            profileJson.put("first_name", profile.getFirstName());
            profileJson.put("last_name", profile.getLastName());
            profileJson.put("linkedin", "linkedin_test");
            profileJson.put("modified", "2019-10-29T20:12:30Z");
            profileJson.put("phone", profile.getPhone());
            profileJson.put("twitter", "twitter_test");
            profileJson.put("user_id", user.getId());
            profileJson.put("website", "www.website_test.com");

            request.put("profile",profileJson);

            retorno = request.toString();
        } catch (JSONException e) {
        }
        return retorno;
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


    private JSONObject fetchProfileResponse(ResponseBody response){
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response.string());
        } catch (JSONException e) {
            //jsonResponse = null
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (NullPointerException e){
            //e.printStackTrace();
        }
        return  jsonResponse;
    }

    private String fetchTokenResponse(ResponseBody response){
        String retorno = null;

        try {
            retorno = new JSONObject(response.string()).getString("jwt");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    private String fetchUserIdResponse(String jwtToken){
        String retorno = null;
        try {
            JWT jwtResponse = JWTUtils.decode(jwtToken);
            JSONObject jwtJson = new JSONObject(jwtResponse.getBody());

            retorno = jwtJson.getString("sub");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return retorno;
    }
}
