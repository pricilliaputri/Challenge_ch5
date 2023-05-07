package com.punyacile.challenge_ch5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.punyacile.challenge_ch5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("InsertAcc", Context.MODE_PRIVATE)

        binding.btnUpdate.setOnClickListener {
            val getUsername = binding.user.text.toString()
            val save = sharedPreferences.edit()
            save.putString("uss", getUsername)
            save.apply()
            startActivity(Intent(this, HomeActivity::class.java))
            Toast.makeText(this, "Profile Update Success", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnLogout.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}