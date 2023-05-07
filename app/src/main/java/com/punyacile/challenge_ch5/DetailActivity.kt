package com.punyacile.challenge_ch5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.punyacile.challenge_ch5.databinding.ActivityDetailBinding
import com.punyacile.challenge_ch5.viewmodel.MovieViewmodel

class DetailActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewmodel
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getDetail = intent.getIntExtra("id", -1)
        viewModel = ViewModelProvider(this).get(MovieViewmodel::class.java)
        viewModel.liveDetail.observe(this){movie ->
            if (movie != null) {
                bindMovieData(movie)
            }

        }
        viewModel.getMovieDetail(getDetail)
    }
    private fun bindMovieData(movie: com.punyacile.challenge_ch5.model.Result) {
        if (movie != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imageView2)

                judul.text = movie.title
                Tanggal.text = movie.releaseDate
                Description.text = movie.overview
            }
        }
    }

}