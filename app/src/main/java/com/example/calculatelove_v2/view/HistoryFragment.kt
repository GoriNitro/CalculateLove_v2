package com.example.calculatelove_v2.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatelove.R
import com.example.calculatelove.databinding.FragmentHistoryBinding
import com.example.calculatelove_v2.di.App
import com.example.calculatelove_v2.remote.LoveModel

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHistory.movementMethod = ScrollingMovementMethod()
        initListener()
    }

    private fun initListener() {
        val sortedList = App.appDatabase.loveDao().getSortedItemsByName()

        val sortedText = sortedList.joinToString("\n") {
            "${it.firstName} ${it.secondName}\n${it.percentage}\n${it.result}\n"
        }
        binding.tvHistory.text = sortedText

        val list = App.appDatabase.loveDao().getAll()
        var result = ""
        list.forEach {
            result += (
                    it.firstName + "\n" + it.secondName +
                            "\n" + it.percentage + "\n" + it.result + "\n"
                    )
        }
        binding.tvHistory.text = sortedText
        binding.btnDelete.setOnLongClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ ->
                    App.appDatabase.loveDao().deleteAll()
                    updateResult(list, result)
                }.show()
            true
        }
        binding.tvHistory.text = sortedText
        binding.btnDelete.setOnClickListener {
            App.appDatabase.loveDao().deleteByName(binding.etName.text.toString())
            updateResult(list, result)
        }
    }

    private fun updateResult(list: List<LoveModel>, result: String) {
        var list1 = list
        var result1 = result
        list1 = App.appDatabase.loveDao().getAll()

        result1 = ""
        list1.forEach {
            result1 += (
                    it.firstName + "\n" + it.secondName +
                            "\n" + it.percentage + "\n" + it.result + "\n\n"
                    )
        }
        binding.tvHistory.text = result1
    }
}