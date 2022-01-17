package uz.pdp.newcurrency.ui.slideshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import uz.pdp.newcurrency.databinding.FragmentSlideshowBinding
import uz.pdp.newcurrency.models.Currency

private const val ARG_PARAM1 = "key"

class SlideshowFragment : Fragment() {

    private var param1: Currency? = null

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Currency?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(param1 != null)
        {
            Picasso.get()
                .load("https://nbu.uz/local/templates/nbu/images/flags/${param1?.code}.png")
                .into(binding.flagImage)

            Picasso.get()
                .load("https://flagcdn.com/w160/uz.png")
                .into(binding.flagImage2)

            binding.countryTv.text = param1?.code
            binding.countryTv2.text = "UZS"

            binding.buyTv.text = param1?.nbu_buy_price
            binding.cellTv.text = param1?.nbu_cell_price

            createObservable().subscribe{
                if(it != ""){
                    binding.cellTv.text = (it.toDouble() * param1?.nbu_cell_price!!.toDouble()).toString()
                    binding.buyTv.text = (it.toDouble() * param1?.nbu_buy_price!!.toDouble()).toString()
                }
                else
                {
                    binding.cellTv.text = param1?.nbu_cell_price
                    binding.buyTv.text = param1?.nbu_buy_price
                }
            }
        }
        else
        {
            Picasso.get()
                .load("https://nbu.uz/local/templates/nbu/images/flags/USD.png")
                .into(binding.flagImage)

            Picasso.get()
                .load("https://flagcdn.com/w160/uz.png")
                .into(binding.flagImage2)

            binding.countryTv.text = "USD"
            binding.countryTv2.text = "UZS"

            createObservable().subscribe{
                if(it != ""){
                    binding.cellTv.text = (it.toDouble() * param1?.nbu_cell_price!!.toDouble()).toString()
                    binding.buyTv.text = (it.toDouble() * param1?.nbu_buy_price!!.toDouble()).toString()
                }
                else
                {
                    binding.cellTv.text = param1?.nbu_cell_price
                    binding.buyTv.text = param1?.nbu_buy_price
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createObservable(): io.reactivex.Observable<String>{
        return io.reactivex.Observable.create { emitter ->
            binding.comeEdit.addTextChangedListener {
                emitter.onNext(it.toString())
            }
        }
    }
}