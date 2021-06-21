package com.example.movie.network

import com.example.movie.getApiKey
import com.example.movie.getKRLanguage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") id : Int,
        @Query("api_key") key: String,
        @Query("page") page : Int,
        @Query("language") lan : String
    ): Call<Movie>

    @GET("trending/{media_type}/{time_window}")
    fun getTrendingMovieList(
        @Path("media_type") mediaType : String?,     // select => [all, movie, tv, person]
        @Path("time_window") timeWindow : String?,   // select => [day, week]
        @Query("api_key") apiKey : String?= getApiKey()
    ): Call<TrendingList>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : Long?,
        @Query("api_key") apiKey: String?= getApiKey(),
        @Query("language") language : String?= getKRLanguage()
    ): Call<MovieDetail>

    @GET("movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId : Long?,
        @Query("api_key") apiKey: String?= getApiKey(),
        @Query("language") language : String?= getKRLanguage()
    ): Call<CreditsList>
}