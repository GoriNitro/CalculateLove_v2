package com.example.calculatelove_v2.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calculatelove.R
import com.example.calculatelove.databinding.FragmentCalculateBinding
import com.example.calculatelove_v2.LoveViewModel
import com.example.calculatelove_v2.di.App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculateFragment : Fragment() {
    private val viewModel: LoveViewModel by viewModels()
    lateinit var binding: FragmentCalculateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        with(binding) {
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            btnCalculate.setOnClickListener {
                viewModel.getLove(
                    firstName = firstName.text.toString(),
                    secondName = secondName.text.toString()
                ).observe(requireActivity()) {
                    it?.let { model ->
                        App.appDatabase.loveDao().insertLove(model)
                        tvResult.text =
                            "${model.firstName} \n${model.secondName} \n${model.percentage} \n${model.result}"
                    }
                }
                /*
                viewModel.getLove(
                    firstName = firstName.text.toString(),
                    secondName = secondName.text.toString()
                ).observe(this@MainActivity, Observer {
                    it?.let { model ->
                        "${model.firstName} \n${model.secondName} \n${model.percentage} \n${model.result}"
                    }
                })*/
            }
        }
    }
}