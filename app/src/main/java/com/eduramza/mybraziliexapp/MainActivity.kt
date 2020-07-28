package com.eduramza.mybraziliexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eduramza.mybraziliexapp.ui.adapter.MyViewPagerAdapter
import com.eduramza.mybraziliexapp.ui.balance.BalanceFragment
import com.eduramza.mybraziliexapp.ui.main.MainFragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(my_toolbar)

        setupViewPager()
        setupAds()
    }

    private fun setupViewPager(){
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Moedas")
        adapter.addFragment(BalanceFragment(), "Saldo")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupAds(){
        val adRequest = AdRequest.Builder().build()
        my_adView.loadAd(adRequest)
    }
}