package com.eduramza.mybraziliexapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.ui.adapter.RemoteCryptoAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: RemoteCryptoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        setupObservers()
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
        viewModel.status.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.updateAdapter(it as MutableList<Tickers.Coin>)
            viewModel.updateLocalDatabase()
        })
    }

}