package com.miedo.detodoaqui.Data.Remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator {

    private static final String API_URL_BASE = "https://dtodoaqui.pw/";

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static final Retrofit.Builder builderScalar = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl(API_URL_BASE)
            .addConverterFactory(ScalarsConverterFactory.create());

    private static final Retrofit retrofitScalar = builderScalar.build();

    public static <S> S createServiceScalar(Class<S> serviceClass) {
        return retrofitScalar.create(serviceClass);
    }


}
