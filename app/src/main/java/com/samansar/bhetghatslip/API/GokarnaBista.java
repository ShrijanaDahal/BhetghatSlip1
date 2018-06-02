package com.samansar.bhetghatslip.API;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GokarnaBista {

    @GET("Appointment/requestedList")
    Call<JsonElement> getRequestList();

    @GET("Appointment/approvedList")
    Call<JsonElement> getApproveList();

    @GET("Appointment/rejectedList")
    Call<JsonElement> getRejectionList();



    @FormUrlEncoded
    @POST("Appointment/responseAppointment")
    Call<JsonElement> sendDataApprove(@Field("status") String status,
                                       @Field("meeting_date") String meeting_date,
                                       @Field("starting_time") String starting_time,
                                       @Field("ending_time") String ending_time,
                                       @Field("id") String id);

    @FormUrlEncoded
    @POST("Appointment/responseAppointment")
    Call<JsonElement> sendDataRejection(@Field("id") String id,
                                      @Field("status") String status);

}
