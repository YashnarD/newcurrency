package uz.pdp.newcurrency

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.pdp.newcurrency.adapters.HistoryAdapter
import uz.pdp.newcurrency.databinding.FragmentAboutBinding
import uz.pdp.newcurrency.manager.MyWorkManager
import uz.pdp.newcurrency.models.Currency
import uz.pdp.newcurrency.networkService.NetworkHelper
import uz.pdp.newcurrency.retrofit.ApiClient
import uz.pdp.newcurrency.retrofit.ApiService
import uz.pdp.newcurrency.room.database.AppDatabase
import uz.pdp.newcurrency.room.entity.MapCurrency
import uz.pdp.newcurrency.ui.home.HomeViewModel
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Int? = null
    private lateinit var binding: FragmentAboutBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var currencyReverse: ArrayList<Currency>
    private lateinit var networkHelper: NetworkHelper
    private lateinit var appDatabase: AppDatabase
    private lateinit var apiService: ApiService
    private lateinit var myList: ArrayList<MapCurrency>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAboutBinding.inflate(inflater, container, false)

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        currencyReverse = ArrayList()

        networkHelper = NetworkHelper(requireContext())
        appDatabase = AppDatabase.getInstance(requireContext())
        apiService = ApiClient.apiService
        myList = ArrayList()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val workRequest2 = PeriodicWorkRequest.Builder(MyWorkManager::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(requireContext())
            .enqueue(workRequest2)

        homeViewModel.getCurrency().observe(requireActivity(), Observer {
            currencyReverse = it.reversed() as ArrayList<Currency>
            for (currency in currencyReverse) {

                when(param2)
                {
                    0 -> {
                        if(currency.code == "USD"){
                            if(isCellOrBuy("USD", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price

                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard1.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }

                    1 -> {
                        if(currency.code == "RUB"){
                            if(isCellOrBuy("RUB", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard2.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    2 -> {
                        if(currency.code == "KZT"){
                            if(isCellOrBuy("KZT", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard3.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    3 -> {
                        if(currency.code == "JPY"){
                            if(isCellOrBuy("JPY", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard4.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    4 -> {
                        if(currency.code == "GBP"){
                            if(isCellOrBuy("GBP", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard5.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    5 -> {
                        if(currency.code == "EUR"){
                            if(isCellOrBuy("EUR", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard6.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    6 -> {
                        if(currency.code == "CNY"){
                            if(isCellOrBuy("CNY", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard7.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    7 -> {
                        if(currency.code == "CHF"){
                            if(isCellOrBuy("CHF", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard8.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    8 -> {
                        if(currency.code == "AUD"){
                            if(isCellOrBuy("AUD", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard9.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                    9 -> {
                        if(currency.code == "AED"){
                            if(isCellOrBuy("AED", currency))
                            {
                                binding.newCellTv.text = currency.nbu_cell_price
                                binding.newBuyTv.text = currency.nbu_buy_price
                            }
                            else
                            {
                                binding.newCellTv.text = "Mavjud emas"
                                binding.newBuyTv.text = "Mavjud emas"
                                binding.countryTv.text = ""
                                binding.countryTv2.text = ""
                            }
                            binding.newDateTv.text = currency.date.substring(0,10)
                        }
                        binding.itemCard10.setCardBackgroundColor(Color.parseColor("#52A956"))
                    }
                }
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }

    private fun isCellOrBuy(code: String, currency: Currency): Boolean {

        var isHave = false

        if(currency.code == code)
        {
            if(currency.nbu_cell_price != "")
                isHave = true
        }
        return isHave
    }
}