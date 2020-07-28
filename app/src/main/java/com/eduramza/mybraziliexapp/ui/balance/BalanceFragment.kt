package com.eduramza.mybraziliexapp.ui.balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.ui.adapter.LocalCryptoAdapter
import kotlinx.android.synthetic.main.balance_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

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

    private fun setupObservers(){
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.updateAdapter(it as MutableList<LocalCurrencies>)
        })
    }

    override fun openQtdeEditor() {
        //Do nothing
    }

    override fun closeQtdeEditor() {
        //Do nothing
    }

}