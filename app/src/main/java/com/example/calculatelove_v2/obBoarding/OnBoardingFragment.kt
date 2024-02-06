package com.example.calculatelove_v2.obBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculatelove.R
import com.example.calculatelove.databinding.FragmentOnBoardingBinding
import com.example.calculatelove_v2.data.Pref
import com.example.calculatelove_v2.view.CalculateFragment
import com.example.calculatelove_v2.obBoarding.adapter.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    @Inject
    lateinit var pref: Pref
    private lateinit var binding: FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
    }

    private fun onClick() {
        pref.onShowed()
        findNavController().navigate(R.id.calculateFragment)
    }
}