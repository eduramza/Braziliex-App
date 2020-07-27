package com.eduramza.mybraziliexapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRL
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRLVol
import com.eduramza.mybraziliexapp.extensions.getCryptoName
import com.eduramza.mybraziliexapp.extensions.returnPercentWithSymbol
import kotlinx.android.synthetic.main.item_list_coins.view.*


const val POSITIVE_COLOR = "#27B52C"
const val NEGATIVE_COLOR = "#FB3527"

class RemoteCryptoAdapter(
    private val list: MutableList<Tickers.Coin>
):RecyclerView.Adapter<RemoteCryptoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_coins))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun updateAdapter(items: MutableList<Tickers.Coin>){
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: Tickers.Coin

        fun bindView(coin: Tickers.Coin) {
            this.item = coin

            itemView.tv_coin_name.text = item.market.getCryptoName()
            itemView.tv_coin_price.text = item.last.convertDoubleToBRL()
            itemView.tv_coin_percent.text = item.percentChange.returnPercentWithSymbol()
            setupPercent(item)
            itemView.tv_vol_value.text = item.quoteVolume24.convertDoubleToBRLVol()
        }

        private fun setupPercent(item: Tickers.Coin){
            if (item.percentChange >= 0) {
                itemView.tv_coin_percent.setTextColor(Color.parseColor(POSITIVE_COLOR))
            } else {
                itemView.tv_coin_percent.setTextColor(Color.parseColor(NEGATIVE_COLOR))
            }
        }

    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}