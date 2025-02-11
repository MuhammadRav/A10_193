package com.example.finalproject.service

import com.example.finalproject.model.AllPekerjaResponse
import com.example.finalproject.model.Pekerja
import com.example.finalproject.model.PekerjaDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PekerjaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("pekerja/")
    suspend fun getAllPekerja(): AllPekerjaResponse

    @GET("pekerja/{id_pekerja}")
    suspend fun getPekerjaById(@Path("id_pekerja") id_pekerja: String): PekerjaDetailResponse

    @POST("pekerja/pekerja_store")
    suspend fun insertPekerja(@Body pekerja: Pekerja)

    @PUT("pekerja/{id_pekerja}")
    suspend fun updatePekerja(@Path("id_pekerja") id_pekerja: String, @Body pekerja: Pekerja)

    @DELETE("pekerja/{id_pekerja}")
    suspend fun deletePekerja(@Path("id_pekerja") id_pekerja: String): retrofit2.Response<Void>
}