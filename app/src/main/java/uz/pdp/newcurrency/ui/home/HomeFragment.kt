package uz.pdp.newcurrency.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.pdp.newcurrency.adapters.CategoryAdapter
import uz.pdp.newcurrency.adapters.HistoryAdapter
import uz.pdp.newcurrency.databinding.FragmentHomeBinding
import uz.pdp.newcurrency.databinding.ItemTabBinding
import uz.pdp.newcurrency.models.Currency

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var titleList: ArrayList<String>
    private lateinit var categoryAdapter: CategoryAdapter
    private val TAG = "HomeFragment"
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var currencyList: ArrayList<Currency>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        titleList = ArrayList()

        currencyList = ArrayList()

        historyAdapter = HistoryAdapter(currencyList)

        homeViewModel.getCurrency().observe(requireActivity(), Observer {
            val newList: ArrayList<Currency> = it.reversed() as ArrayList<Currency>
            val oldList: ArrayList<Currency> = ArrayList()
            for (currency in newList) {
                if (currency.code == "USD"
                    || currency.code == "RUB"
                    || currency.code == "KZT"
                    || currency.code == "JPY"
                    || currency.code == "GBP"
                    || currency.code == "EUR"
                    || currency.code == "CHF"
                    || currency.code == "AED"
                    || currency.code == "AUD"
                    || currency.code == "CNY"
                ){
                    oldList.add(currency)
                    titleList.add(currency.code)
                }
                historyAdapter = HistoryAdapter(oldList)
                binding.rv.adapter = historyAdapter
            }
            categoryAdapter = CategoryAdapter(this, titleList)
            binding.viewPager.adapter = categoryAdapter

            TabLayoutMediator(
                binding.tabLayout1,
                binding.viewPager,
                true,
                object : TabLayoutMediator.TabConfigurationStrategy {
                    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                        tab.setText(titleList[position])
                        val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
                        itemTabBinding.tv.setText(tab.text)
                        if (position == 0) {
                            itemTabBinding.tv.setTextColor(Color.WHITE)
                            itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#4FA35B"))
                        } else {
                            itemTabBinding.tv.setTextColor(Color.parseColor("#B3B3B3"))
                            itemTabBinding.card.setCardBackgroundColor(Color.WHITE)
                        }
                        tab.setCustomView(itemTabBinding.root)
                    }

                }).attach()

            binding.tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                    itemTabBinding.tv.setTextColor(Color.WHITE)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#4FA35B"))
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                    itemTabBinding.tv.setTextColor(Color.parseColor("#B3B3B3"))
                    itemTabBinding.card.setCardBackgroundColor(Color.WHITE)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}