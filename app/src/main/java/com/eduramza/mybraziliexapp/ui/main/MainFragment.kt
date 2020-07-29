package com.eduramza.mybraziliexapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.ui.adapter.RemoteCryptoAdapter
import com.eduramza.mybraziliexapp.ui.balance.BalanceViewModel
import kotlinx.android.synthetic.main.generic_error.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private val balanceViewModel: BalanceViewModel by viewModel()
    private lateinit var adapter: RemoteCryptoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        setupObservers()
        setListeners()
    }

    private fun setupList() {
        adapter = RemoteCryptoAdapter(mutableListOf())

        rv_braziliex_coins.layoutManager = LinearLayoutManager(context)
        rv_braziliex_coins.addItemDecoration(
            DividerItemDecoration(
                rv_braziliex_coins.context,
                DividerItemDecoration.VERTICAL
            ))
        rv_braziliex_coins.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            when(status){
                MyStatus.DONE -> closeLoading()
                MyStatus.LOADING -> showLoading()
                MyStatus.ERROR -> showErrorMessage()
            }
        })

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.updateAdapter(it as MutableList<Tickers.Coin>)
        })
    }

    private fun setListeners(){
        bt_error_try_again.setOnClickListener {
            tryAgain()
        }
    }

    private fun closeLoading(){
        progress.visibility = GONE
        rv_braziliex_coins.visibility = VISIBLE
        updateLocal()
    }

    private fun updateLocal(){
        viewModel.updateLocalDatabase()
    }

    private fun showLoading(){
        progress.visibility = VISIBLE
        rv_braziliex_coins.visibility = GONE
    }

    private fun showErrorMessage(){
        progress.visibility = GONE
        container_error.visibility = VISIBLE
    }

    private fun tryAgain(){
        progress.visibility = VISIBLE
        container_error.visibility = GONE
        viewModel.getAllTickers()
    }

}