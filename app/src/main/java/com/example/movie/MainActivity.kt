      package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.movie.adapter.TrendingAdapter
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.network.Movie
import com.example.movie.network.MovieService
import com.example.movie.network.TrendingList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        retrofitSetting().create(MovieService::class.java).getTrendingMovieList("movie", "day").enqueue(object : Callback<TrendingList>{
            override fun onResponse(call: Call<TrendingList>, response: Response<TrendingList>) {
                Log.d("Main Trending", "${response.body()}")
                val trendingList = response.body()
                val imagePathList = mutableListOf<String>()
                trendingList?.results?.forEach{
                    Log.e("test", "${it.backdrop_path}")
                    imagePathList.add("https://image.tmdb.org/t/p/original/${it.backdrop_path}")
                }

                binding.vpTrending.apply {
                    adapter = TrendingAdapter(this@MainActivity, imagePathList)
                    offscreenPageLimit = 3
                    setPageTransformer(SliderTransformer(3))
                }
            }

            override fun onFailure(call: Call<TrendingList>, t: Throwable) {
                Log.d("Main Trending", "${t.printStackTrace()}")
            }

        })

    }
}