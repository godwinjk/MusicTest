package com.godwin.testmusic.network

import com.godwin.testmusic.network.model.ITuneResponse
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ICloudApiService {

    @GET("us/rss/topalbums/limit={limit}/json")
    suspend fun fetchSongs(@Path("limit") limit: Int = 100): ITuneResponse

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/";

        fun create(): ICloudApiService {
            val contentType = "application/json".toMediaType()

            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ICloudApiService::class.java)
        }
    }
}