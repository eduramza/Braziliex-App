package com.eduramza.mybraziliexapp.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRL
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRLVol
import com.eduramza.mybraziliexapp.extensions.getCryptoName
import com.eduramza.mybraziliexapp.extensions.returnPercentWithSymbol
import kotlinx.android.synthetic.main.item_list_balance.view.*
import kotlinx.android.synthetic.main.item_list_coins.view.*

class LocalCryptoAdapter (private val list: MutableList<LocalCurrencies>,
                          private val listener: LocalAdapterListener
): RecyclerView.Adapter<LocalCryptoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_balance))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun updateAdapter(items: MutableList<LocalCurrencies>){
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: LocalCurrencies

        @SuppressLint("SetTextI18n")
        fun bindView(coin: LocalCurrencies) {
            this.item = coin

            itemView.tv_coin_name_b.text = item.coin_name
            itemView.tv_coin_total_price_b.text = (item.qtde * item.unit_price).convertDoubleToBRL()
            itemView.tv_qtde_value.text = "${item.coin_nickname} ${item.qtde}"
            itemView.et_qtde_b.setText(item.qtde.toString())

            itemView.ic_edit_qtde_b.setOnClickListener {
                if (itemView.et_qtde_b.visibility == GONE){

                    listener.openQtdeEditor()
                    itemView.tv_qtde_value.visibility == GONE
                    itemView.et_qtde_b.visibility == VISIBLE

                } else {
                    listener.closeQtdeEditor()
                    itemView.tv_qtde_value.text = "${item.coin_nickname} ${itemView.et_qtde_b.text}"
                }
            }
        }

    }

    interface LocalAdapterListener{
        fun openQtdeEditor()
        fun closeQtdeEditor()
    }
}