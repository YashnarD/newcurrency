package uz.pdp.newcurrency.manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.pdp.newcurrency.models.Currency
import uz.pdp.newcurrency.networkService.NetworkHelper
import uz.pdp.newcurrency.retrofit.ApiClient
import uz.pdp.newcurrency.retrofit.ApiService
import uz.pdp.newcurrency.room.database.AppDatabase
import uz.pdp.newcurrency.room.entity.MapCurrency

class MyWorkManager(var context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {

    lateinit var appDatabase: AppDatabase
    lateinit var apiService: ApiService
    lateinit var networkHelper: NetworkHelper

    override fun doWork(): Result {

        apiService = ApiClient.apiService
        appDatabase = AppDatabase.getInstance(context)
        networkHelper = NetworkHelper(context)

        if(networkHelper.isNetworkConnected())
        {
            apiService.getCurrentCurrency().enqueue(object : Callback<List<Currency>>{
                override fun onResponse(
                    call: Call<List<Currency>>,
                    response: Response<List<Currency>>
                ) {
                    if(response.isSuccessful)
                    {
                        var count = 0
                        response.body()?.forEach {
                            val allCurrency = appDatabase.currencyDao().getAllCurrency() as ArrayList<MapCurrency>
                            for (mapCurrency in allCurrency) {
                                if(mapCurrency.date == it.date){
                                    count++
                                    break
                                }
                            }
                            if(count == 1){
                                val currencyModels = MapCurrency(date = it.date,
                                    cell = it.nbu_cell_price, buy = it.nbu_buy_price,
                                    code = it.code
                                )
                                appDatabase.currencyDao().addCurrency(currencyModels)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                }

            })
        }
        return Result.success()
    }


}