package com.miedo.detodoaqui.Data.Remote;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CesarFakeAPI {

    @POST("/api/sign_in")
    public Call<ResponseBody> LoginUser(@Body RequestBody body);

    @POST("/api/sign_up")
    public Call<ResponseBody> RegisterUser(@Body RequestBody body);

    @POST("/api/my_user")
    public Call<ResponseBody> GetUser(@Body RequestBody body);

}
