package com.miedo.detodoaqui.Models;

import com.google.gson.JsonObject;
import com.miedo.detodoaqui.Data.EstablishmentSearch;
import com.miedo.detodoaqui.Data.Remote.CesarFakeAPI;
import com.miedo.detodoaqui.Data.Remote.ServiceGenerator;
import com.miedo.detodoaqui.Data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstablishmentsSearchModel {

    public void SearchEstablishments(String keyword, String location, String category){
        // Creamos la instancia de la api
        CesarFakeAPI api = ServiceGenerator.createServiceScalar(CesarFakeAPI.class);

        Map<String, String> params = new HashMap<String,String>();
        params.put("keyword",keyword);
        params.put("find_location",location);
        params.put("categories",category);

        // Realizamos la peticion asincrona
        Call<ResponseBody> call = api.SearchEstablishments(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    JSONArray jsonArrayResponse = fetchStablishmentsJsonArray(response.body());

                    List<EstablishmentSearch> list = new ArrayList();

                    for(int i = 0; i < jsonArrayResponse.length(); i++){
                        //list.
                    }

                } else {
                    //liveUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //liveUser.setValue(null);
            }
        });
    }

    private JSONArray fetchStablishmentsJsonArray(ResponseBody response){
        JSONObject jsonResponse = null;
        JSONArray retorno = null;
        try {
            jsonResponse = new JSONObject(response.string());
            retorno = jsonResponse.getJSONArray("data");
        } catch (JSONException e) {
            //jsonResponse = null
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (NullPointerException e){
            //e.printStackTrace();
        }
        return retorno;
    }

}
