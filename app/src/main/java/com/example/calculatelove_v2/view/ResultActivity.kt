package com.example.calculatelove_v2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatelove.databinding.ActivityResultBinding
import com.example.calculatelove_v2.remote.LoveModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.getSerializableExtra("result") as LoveModel
        binding.tvResult.text = intent.result
        binding.tvPercentage.text = intent.percentage
        binding.tvFirstName.text = intent.firstName
        binding.tvSecondName.text = intent.secondName
    }
}