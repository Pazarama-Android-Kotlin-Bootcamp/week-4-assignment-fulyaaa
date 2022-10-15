package com.example.week_4_assigment.api;

import com.example.week_4_assigment.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("onecall")
    Call<Weather> weatherList(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String apiKey, @Query("exclude") String exclude);
}
