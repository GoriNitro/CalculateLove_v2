package com.example.calculatelove_v2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.calculatelove_v2.remote.LoveApi
import com.example.calculatelove_v2.remote.LoveModel
import com.example.calculatelove_v2.remote.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: LoveApi) {

    fun getLoveModel(firstName: String, secondName: String): MutableLiveData<LoveModel> {
        val mutableLiveData = MutableLiveData<LoveModel>()

        api.getCompatibility(firstName = firstName, secondName = secondName).enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.isSuccessful) {
                        val model = response.body()!!
                        mutableLiveData.postValue(model)
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo", "onFailure:  ${t.message}")
                }
            })
        return mutableLiveData
    }
}