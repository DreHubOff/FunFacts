package com.studying.funfacts.network

import com.studying.funfacts.network.model.Fact
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiService {
    private const val DEF_AMOUNT = 100
    private const val END_POINT = "https://cat-fact.herokuapp.com/facts/"
    private var factsApi: FactsApi

    fun getData(animalType: String) =
        factsApi.weatherData(animalType, DEF_AMOUNT)

    interface FactsApi {
        @GET("random")
        fun weatherData(
            @Query("animal_type") animal_type: String,
            @Query("amount") amount: Int
        ): Call<List<Fact>>
    }


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        factsApi = retrofit.create(FactsApi::class.java)
    }
}