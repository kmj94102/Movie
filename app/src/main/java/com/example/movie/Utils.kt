package com.example.movie

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getApiKey() : String{
    return "2d40e399b0f2a4082fa534579a47050f"
}

fun getKRLanguage() : String{
    return "ko-KR"
}

fun getStartImageUrl() : String{
    return "https://image.tmdb.org/t/p/original/"
}

fun retrofitSetting() : Retrofit{
    return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}