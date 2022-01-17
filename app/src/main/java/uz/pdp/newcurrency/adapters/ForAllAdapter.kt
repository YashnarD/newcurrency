package uz.pdp.newcurrency.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.newcurrency.databinding.ItemForAllBinding
import uz.pdp.newcurrency.databinding.ItemForRvHistoryBinding
import uz.pdp.newcurrency.models.Currency

class ForAllAdapter(var list: ArrayList<Currency>, var listener: OnItemClick) :
    RecyclerView.Adapter<ForAllAdapter.Vh>() {

    inner class Vh(var itemForAllBinding: ItemForAllBinding) :
        RecyclerView.ViewHolder(itemForAllBinding.root) {
        fun onBind(currency: Currency, position: Int) {
            if (currency.nbu_cell_price == "") {
                itemForAllBinding.cellTv.text = "Mavjud emas"
                itemForAllBinding.buyTv.text = "Mavjud emas"
            } else {
                itemForAllBinding.buyTv.text = "${currency.nbu_buy_price} UZS"
                itemForAllBinding.cellTv.text = "${currency.nbu_cell_price} UZS"
            }

            itemForAllBinding.countryTv.text = currency.code
            Picasso.get()
                .load("https://nbu.uz/local/templates/nbu/images/flags/${currency.code}.png")
                .into(itemForAllBinding.flagImage)
            itemForAllBinding.cardBtn.setOnClickListener {
                listener.onCalculatorClick(currency, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemForAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val currency = list[position]
        holder.onBind(currency, position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClick {
        fun onCalculatorClick(currency: Currency, position: Int)
    }
}