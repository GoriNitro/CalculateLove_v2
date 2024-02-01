package com.example.calculatelove_v2.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://love-calculator.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    var api: LoveApi = retrofit.create(LoveApi::class.java)
}