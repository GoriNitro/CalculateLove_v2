package com.example.calculatelove_v2.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.calculatelove.databinding.ActivityMainBinding
import com.example.calculatelove_v2.LoveViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        with(binding) {
            btnCalculate.setOnClickListener {
                viewModel.getLove(
                    firstName = firstName.text.toString(),
                    secondName = secondName.text.toString()
                ).observe(this@MainActivity, Observer {
                    it?.let {model->
                        "${model.firstName} \n${model.secondName} \n${model.percentage} \n${model.result}"
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra("result", model)
                        startActivity(intent)
                    }

                })
            }
        }
    }
}