package com.example.android.searchdebounce.networking

import com.example.programmingtask.model.ImageSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("rest/")
    suspend fun fetchImages(@Query("method") method : String,
                            @Query("format") format :String,
                            @Query("nojsoncallback") nojsoncallback :Int,
                            @Query("text") text :String,
                            @Query("page") page :Int,
                            @Query("per_page") per_page :Int,
                            @Query("api_key") api_key :String): ImageSearchResponse
}
