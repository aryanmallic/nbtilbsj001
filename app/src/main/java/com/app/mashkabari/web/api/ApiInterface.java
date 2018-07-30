package com.app.mashkabari.web.api;

import com.app.mashkabari.model.modelCategories.ModelCategories;
import com.app.mashkabari.model.modelDepartment.ModelDepartment;
import com.app.mashkabari.model.modlDetails.ModelDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    /*@GET("allpost")
    Call<List<ModelPosts>> getPost(@Header("Authorization") String auth,
                                   @Query("offset") String offset,
                                   @Query("limit") String limit);*/

    @GET("department/{id}/category/")
    Call<ModelCategories> getCategories(
            @Path("id") int id,
            @Query("format") String json,
            @Query("access_token") String token);


    @GET("department")
    Call<ArrayList<ModelDepartment>> getDepartment(@Query("format") String json,
                                                   @Query("access_token") String token);


    @GET("product/{id}/")
    Call<ModelDetails> getDetails(@Path("id") int id,
                                  @Query("format") String json,
                                  @Query("access_token") String token);
}
