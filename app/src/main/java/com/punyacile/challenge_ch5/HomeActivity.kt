package com.punyacile.challenge_ch5

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.challenge_ch5.databinding.ActivityHomeBinding
import com.punyacile.challenge_ch5.viewmodel.MovieViewmodel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("InsertAcc", MODE_PRIVATE)
        getData()

        var getUser = sharedPreferences.getString("uss", "")
        binding.welcome.text ="Welcome, $getUser"
        binding.imageView.setOnClickListener {
            var giveUser = sharedPreferences.edit()
            giveUser.putString("uss", getUser)
            giveUser.apply()
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
    fun getData(){
        movieAdapter = MovieAdapter(emptyList()){movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", movie.id)
            startActivity(intent)
        }
        val getVM = ViewModelProvider(this).get(MovieViewmodel::class.java)
        getVM.getMovie()
        binding.rcvcon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvcon.adapter = movieAdapter
        getVM.liveDataMovie.observe(this,{ movie ->
            movieAdapter.listMovie = movie
            movieAdapter.notifyDataSetChanged()
        })

    }

}