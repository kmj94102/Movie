package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movie.adapter.CastInfoAdapter
import com.example.movie.adapter.GenreAdapter
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.network.CreditsList
import com.example.movie.network.MovieDetail
import com.example.movie.network.MovieService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val backgroundImage = intent.getStringExtra(Constants.INTENT_KEY_IMAGE_PATH)
        backgroundImage?.let{
            Glide.with(this).load(it).centerCrop().into(binding.imgBackgtround)
        }
        val movieId = intent.getLongExtra(Constants.INTENT_KEY_MOVIE_ID, -1L)

        if(movieId == -1L){
            toast("movieId is -1L")
            finish()
        }

        // 영화 상세정보 조회
        retrofitSetting().create(MovieService::class.java).getMovieDetail(movieId = movieId).enqueue(object : Callback<MovieDetail>{
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                Log.d("getMovieDetail", "${response.body()}")
                val movieDetail = response.body()
                val genreList = mutableListOf<String>()

                movieDetail?.genres?.forEach {
                    if(!it.name.isNullOrEmpty()){
                        genreList.add(it.name)
                    }
                }

                binding.rvGenre.adapter = GenreAdapter(genreList)
                binding.txtVoteAverage.text = "${movieDetail?.vote_average}"

                binding.txtTitle.text = movieDetail?.title
                binding.txtStory.text = movieDetail?.overview
                binding.txtTitle.isSelected = true
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e("getMovieDetail", "${t.printStackTrace()}")
            }

        })

        // 등장인물 조회
        retrofitSetting().create(MovieService::class.java).getCredits(movieId = movieId).enqueue(object : Callback<CreditsList>{
            override fun onResponse(call: Call<CreditsList>, response: Response<CreditsList>) {
                Log.d("getCredits", "${response.body()}")
                val creditsList = response.body()
                binding.rvCast.adapter = CastInfoAdapter(this@MovieDetailActivity, creditsList?.cast ?: listOf())
            }

            override fun onFailure(call: Call<CreditsList>, t: Throwable) {
                Log.e("getCredits", "${t.printStackTrace()}")
            }
        })
    }
}