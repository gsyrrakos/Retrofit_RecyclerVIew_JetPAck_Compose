package com.example.mvp_graphql.Data

import com.example.mvp_graphql.api.ApiRetrofit
import com.example.mvp_graphql.api.ServiceBuilder

class RepoRetrofit {

    val request = ServiceBuilder.buildService(ApiRetrofit::class.java)



    suspend fun getrest(): List<Restaurants> =request.getRest()

}