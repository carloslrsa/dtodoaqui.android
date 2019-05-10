package com.miedo.detodoaqui.Data.Remote;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CesarFakeAPI {

    @POST("/api/sign_in")
    public Call<ResponseBody> loginUsuario(@Body RequestBody cuerpo);
}
