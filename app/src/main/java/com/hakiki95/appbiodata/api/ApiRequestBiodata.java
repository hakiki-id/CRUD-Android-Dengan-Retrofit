package com.hakiki95.appbiodata.api;

import com.hakiki95.appbiodata.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public interface ApiRequestBiodata {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("nama") String nama,
                                   @Field("usia") String usia,
                                   @Field("domisili") String domisili);

    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData( @Field("id") String id,
                                    @Field("nama") String nama,
                                    @Field("usia") String usia,
                                    @Field("domisili") String domisili);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
}
