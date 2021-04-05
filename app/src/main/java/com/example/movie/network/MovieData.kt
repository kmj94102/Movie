package com.example.movie.network

data class Movie(
    val backdrop_path : String?,
    val overview : String?
)

data class TrendingList(
    val page : Long?,
    val results : List<TrendingListResult>,
    val total_pages : Long?,
    val total_results : Long?
)

data class TrendingListResult(
    val vote_average : Float?,
    val overview : String?,
    val release_date : String?,
    val id : Long?,
    val adult : Boolean?,
    val backdrop_path : String?,
    val genre_ids : List<Int>,
    val vote_count : Long?,
    val original_language : String?,
    val original_title : String?,
    val poster_path : String?,
    val video : Boolean?,
    val title : String?,
    val popularity : Float?,
    val media_type : String?
)