package uz.pdp.newcurrency.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.pdp.newcurrency.models.Currency

interface ApiService {

    @GET("exchange-rates/json/")
    fun getCurrentCurrency(): Call<List<Currency>>
}