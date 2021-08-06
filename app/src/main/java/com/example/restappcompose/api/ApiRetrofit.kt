package com.example.mvp_graphql.api



import com.example.mvp_graphql.Data.Restaurants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface ApiRetrofit {
    companion object{

        const val BASE_URL:String="https://random-data-api.com/api/"





    }



    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRest():List<Restaurants>




}