package com.example.calculatelove_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.calculatelove_v2.remote.LoveModel

class LoveViewModel : ViewModel(){
    private val repository = Repository()

    fun getLove(firstName: String, secondName: String): LiveData<LoveModel>{
        return repository.getLoveModel(firstName = firstName, secondName = secondName)
    }
}