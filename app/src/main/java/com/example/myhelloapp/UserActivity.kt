package com.example.myhelloapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.myhelloapp.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUserBinding.inflate(LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userName = intent.getStringExtra("User Name")
        val userAge = intent.getIntExtra("User Age", 0)

        val nameTextView = binding.name
        val ageTextView = binding.age

        nameTextView.text = userName
        ageTextView.text = "Age: $userAge"
    }
}