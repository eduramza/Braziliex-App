package com.eduramza.mybraziliexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eduramza.mybraziliexapp.ui.adapter.MyViewPagerAdapter
import com.eduramza.mybraziliexapp.ui.balance.BalanceFragment
import com.eduramza.mybraziliexapp.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(my_toolbar)

        setupViewPager()
    }

    private fun setupViewPager(){
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Moedas")
        adapter.addFragment(BalanceFragment(), "Saldo")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}