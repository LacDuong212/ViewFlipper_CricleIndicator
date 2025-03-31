package com.example.baitapviewflipper;

import com.example.baitapviewflipper.Model.MessageModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> loadImageSlider(@Field("position") int position);
}
