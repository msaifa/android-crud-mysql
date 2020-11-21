package com.msaifa.tugas07.api;

import com.msaifa.tugas07.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestBiodata {

    @FormUrlEncoded
    @POST("tambah")
    Call<ResponsModel> tambahUser(
            @Field("unama") String unama,
            @Field("uemail") String uemail,
            @Field("utelp") String utelp,
            @Field("ujk") String ujk,
            @Field("ualamat") String ualamat
    );

    @FormUrlEncoded
    @POST("ambil")
    Call<ResponsModel> getDataUser(
            @Field("pencarian") String pencarian
    );

    @FormUrlEncoded
    @POST("ubah")
    Call<ResponsModel> ubahData(
            @Field("uid") String uid,
            @Field("unama") String unama,
            @Field("uemail") String uemail,
            @Field("utelp") String utelp,
            @Field("ujk") String ujk,
            @Field("ualamat") String ualamat
    );

    @FormUrlEncoded
    @POST("hapus")
    Call<ResponsModel> deleteData(@Field("uid") String uid);
}
