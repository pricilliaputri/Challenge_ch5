package com.punyacile.challenge_ch5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.punyacile.challenge_ch5.model.Result
import com.punyacile.challenge_ch5.model.moviePopular
import com.punyacile.challenge_ch5.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewmodel : ViewModel(){
    lateinit var liveDataMovie: MutableLiveData<List<Result>>
    lateinit var liveDetail: MutableLiveData<com.punyacile.challenge_ch5.model.Result?>

    init {
        liveDataMovie = MutableLiveData()
        liveDetail = MutableLiveData()
    }

    fun getMovie() {
        RetrofitClient.instance.getPopularMovies(
            apiKey = "9bee349cfeda097c0b57c7b6d1b6aa2f",
            page = 1
        ).enqueue(object : Callback<moviePopular<com.punyacile.challenge_ch5.model.Result>> {
            override fun onResponse(call: Call<moviePopular<com.punyacile.challenge_ch5.model.Result>>,
                                    response: Response<moviePopular<com.punyacile.challenge_ch5.model.Result>>) {
                if (response.isSuccessful){
                    val movieresponse = response.body()
                    liveDataMovie.postValue(movieresponse?.results)

                }else{
                    liveDataMovie.value = emptyList()
                }
            }

            override fun onFailure(call: Call<moviePopular<com.punyacile.challenge_ch5.model.Result>>,
                                   t: Throwable) {
                liveDataMovie.value = emptyList()
            }

        })


    }
    fun getMovieDetail(movieId:Int) {
        RetrofitClient.instance.getMovieDetails(movieId, "9bee349cfeda097c0b57c7b6d1b6aa2f")
            .enqueue(object : Callback<com.punyacile.challenge_ch5.model.Result> {
                override fun onResponse(call: Call<com.punyacile.challenge_ch5.model.Result>,
                                        response: Response<com.punyacile.challenge_ch5.model.Result>) {
                    if (response.isSuccessful) {
                        val movie = response.body()
                        liveDetail.value = movie
                    }
                }

                override fun onFailure(call: Call<com.punyacile.challenge_ch5.model.Result>,
                                       t: Throwable) {
                    liveDataMovie.value = emptyList()
                }

            })
    }


}