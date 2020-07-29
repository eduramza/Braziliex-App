package com.eduramza.mybraziliexapp.ui.adapter

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.extensions.changeDotInComma
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRL
import com.eduramza.mybraziliexapp.extensions.toCommaDouble
import kotlinx.android.synthetic.main.item_list_balance.view.*

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
            itemView.tv_qtde_value.text = "${item.coin_nickname} ${item.qtde.changeDotInComma()}"
            itemView.et_qtde_b.setText(item.qtde.changeDotInComma())

            itemView.ic_edit_qtde_b.setOnClickListener {
                if (itemView.til_qtde_b.visibility == GONE){
                    showEditQtde()

                    itemView.et_qtde_b.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                            handleDataChange()
                            hideEditQtde()
                            return@OnKeyListener true
                        }
                        false
                    })

                } else {
                    handleDataChange()
                    hideEditQtde()
                }
            }
        }

        private fun hideEditQtde(){
            itemView.til_qtde_b.visibility = GONE
            itemView.et_qtde_b.visibility = GONE
            itemView.ic_edit_qtde_b.setBackgroundResource(R.drawable.ic_arrow_down)
        }

        private fun showEditQtde(){
            itemView.til_qtde_b.visibility = VISIBLE
            itemView.et_qtde_b.visibility = VISIBLE
            itemView.ic_edit_qtde_b.setBackgroundResource(R.drawable.ic_arrow_up)

        }

        @SuppressLint("SetTextI18n")
        private fun handleDataChange(){
            item.qtde =
                if(itemView.et_qtde_b.text.isNullOrEmpty())  0.0
                else itemView.et_qtde_b.text.toString().toCommaDouble()

            itemView.tv_qtde_value.text = "${item.coin_nickname} ${item.qtde.changeDotInComma()}"
            itemView.tv_coin_total_price_b.text =
                (item.qtde * item.unit_price).convertDoubleToBRL()
            listener.updateQtdeInLocal(item)
        }

    }

    interface LocalAdapterListener{
        fun updateQtdeInLocal(item: LocalCurrencies)
    }

}