package uz.pdp.newcurrency.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.pdp.newcurrency.models.Currency
import uz.pdp.newcurrency.networkService.NetworkHelper
import uz.pdp.newcurrency.retrofit.ApiClient

class HomeViewModel() : ViewModel() {

    private lateinit var networkHelper: NetworkHelper

    fun getCurrency(): LiveData<List<Currency>> {
        var currencyList = MutableLiveData<List<Currency>>()
        ApiClient.apiService.getCurrentCurrency().enqueue(object : Callback<List<Currency>> {
            override fun onResponse(
                call: Call<List<Currency>>,
                response: Response<List<Currency>>
            ) {
                if (response.isSuccessful) {
                    currencyList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
            }
        })
        return currencyList
    }
}