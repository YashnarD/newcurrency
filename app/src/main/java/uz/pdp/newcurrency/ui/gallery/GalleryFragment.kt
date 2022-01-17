package uz.pdp.newcurrency.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import uz.pdp.newcurrency.R
import uz.pdp.newcurrency.adapters.ForAllAdapter
import uz.pdp.newcurrency.adapters.HistoryAdapter
import uz.pdp.newcurrency.databinding.FragmentGalleryBinding
import uz.pdp.newcurrency.models.Currency
import uz.pdp.newcurrency.ui.home.HomeViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var forAllAdapter: ForAllAdapter
    private lateinit var currencyReverse: ArrayList<Currency>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        currencyReverse = ArrayList()
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        forAllAdapter = ForAllAdapter(currencyReverse, object : ForAllAdapter.OnItemClick{
            override fun onCalculatorClick(currency: Currency, position: Int) {
            }

        })

        homeViewModel.getCurrency().observe(requireActivity(), Observer {
            currencyReverse = it.reversed() as ArrayList<Currency>
            forAllAdapter = ForAllAdapter(currencyReverse, object : ForAllAdapter.OnItemClick{
                override fun onCalculatorClick(currency: Currency, position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable("key", currency)
                    Navigation.findNavController(requireView()).navigate(R.id.nav_slideshow, bundle)
                }

            })
            binding.rv.adapter = forAllAdapter
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}