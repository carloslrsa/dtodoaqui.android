package com.miedo.detodoaqui.Data.Remote;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CesarFakeAPI {

    @POST("/api/sign_in")
    public Call<ResponseBody> LoginUser(@Body RequestBody body);

    @POST("/api/sign_up")
    public Call<ResponseBody> RegisterUser(@Body RequestBody body);

    @POST("/api/my_user")
    public Call<ResponseBody> GetUser(@Body RequestBody body);

    @GET("/api/my_profile")
    public Call<ResponseBody> GetProfile(@Header("Authorization") String bearer);

    @POST("/api/profile")
    public Call<ResponseBody> CreateProfile(@Body RequestBody body);

    @PUT("/api/profile/{id}")
    public Call<ResponseBody> UpdateProfile(@Body RequestBody body, @Path("id") String id);

    @GET("/api/search")
    public Call<ResponseBody> SearchEstablishments(@FieldMap Map<String, String> params);

}
