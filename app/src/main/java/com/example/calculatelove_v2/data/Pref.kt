package com.example.calculatelove_v2.data

import android.content.SharedPreferences
import javax.inject.Inject

class Pref @Inject constructor(val pref:SharedPreferences) {

    fun isShow():Boolean{
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onShowed(){
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    companion object{
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
    }
}