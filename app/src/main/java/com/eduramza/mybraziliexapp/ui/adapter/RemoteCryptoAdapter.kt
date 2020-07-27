package com.eduramza.mybraziliexapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRL
import com.eduramza.mybraziliexapp.extensions.returnPercentWithSymbol
import kotlinx.android.synthetic.main.item_list_coins.view.*

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

            itemView.tv_coin_name.text = item.market
            itemView.tv_coin_price.text = item.last.convertDoubleToBRL()
            itemView.tv_coin_percent.text = item.percentChange.returnPercentWithSymbol()
            itemView.tv_vol_value.text = item.baseVolume24.convertDoubleToBRL()
        }

    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}