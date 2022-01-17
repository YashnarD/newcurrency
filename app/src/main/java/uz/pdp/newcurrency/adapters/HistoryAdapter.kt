package uz.pdp.newcurrency.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.newcurrency.databinding.ItemForRvHistoryBinding
import uz.pdp.newcurrency.models.Currency
import uz.pdp.newcurrency.room.entity.MapCurrency

class HistoryAdapter(var list: ArrayList<Currency>) : RecyclerView.Adapter<HistoryAdapter.Vh>() {

    inner class Vh(var itemForRvHistoryBinding: ItemForRvHistoryBinding) :
        RecyclerView.ViewHolder(itemForRvHistoryBinding.root) {
        fun onBind(currency: Currency) {
            if(currency.nbu_cell_price == "")
            {
                itemForRvHistoryBinding.cellTv.text = "Mavjud emas"
                itemForRvHistoryBinding.buyTv.text = "Mavjud emas"
            }
            else
            {
                itemForRvHistoryBinding.buyTv.text = "${currency.nbu_buy_price} UZS"
                itemForRvHistoryBinding.cellTv.text = "${currency.nbu_cell_price} UZS"
            }
            itemForRvHistoryBinding.dateTv.text = currency.date.substring(0, 10)
            itemForRvHistoryBinding.timeTv.text = currency.date.substring(11)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemForRvHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val currency = list[position]
        holder.onBind(currency)
    }

    override fun getItemCount(): Int = list.size


}