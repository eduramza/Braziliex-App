package com.eduramza.mybraziliexapp.ui.balance

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.extensions.convertDoubleToBRL
import com.eduramza.mybraziliexapp.ui.adapter.LocalCryptoAdapter
import kotlinx.android.synthetic.main.balance_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

const val ESTIMATED_BALANCE = "Saldo Estimado: "
class BalanceFragment : Fragment(), LocalCryptoAdapter.LocalAdapterListener {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private val viewModel: BalanceViewModel by viewModel()
    private lateinit var adapter: LocalCryptoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.balance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        setupObservers()
    }

    private fun setupList(){
        adapter = LocalCryptoAdapter(mutableListOf(), this)

        rv_local_coins.layoutManager = LinearLayoutManager(context)
        rv_local_coins.addItemDecoration(
            DividerItemDecoration(
                rv_local_coins.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rv_local_coins.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers(){
        viewModel.getAllCoins()
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.updateAdapter(it as MutableList<LocalCurrencies>)
        })
        viewModel.getBalanceEstimated().observe(viewLifecycleOwner, Observer {
            tv_estimated_balance.text =
                "$ESTIMATED_BALANCE ${it.convertDoubleToBRL()}"
        })
    }

    override fun updateQtdeInLocal(item: LocalCurrencies) {
        closeKeyboard()
        viewModel.updateQtdeCoins(item.qtde, item.id)
    }

    private fun closeKeyboard(){
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun closeQtdeEditor() {
        //Do nothing
    }

    override fun onPause() {
        super.onPause()
        closeKeyboard()
    }

}