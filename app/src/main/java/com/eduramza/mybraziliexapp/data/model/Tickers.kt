package com.eduramza.mybraziliexapp.data.model


import com.google.gson.annotations.SerializedName

data class Tickers(
    @SerializedName("abc_brl")
    val abcBrl: Coin,
    @SerializedName("bch_brl")
    val bchBrl: Coin,
    @SerializedName("bnb_brl")
    val bnbBrl: Coin,
    @SerializedName("brzx_brl")
    val brzxBrl: Coin,
    @SerializedName("btc_brl")
    val btcBrl: Coin,
    @SerializedName("btg_brl")
    val btgBrl: Coin,
    @SerializedName("crw_brl")
    val crwBrl: Coin,
    @SerializedName("dai_brl")
    val daiBrl: Coin,
    @SerializedName("dash_brl")
    val dashBrl: Coin,
    @SerializedName("dcr_brl")
    val dcrBrl: Coin,
    @SerializedName("eos_brl")
    val eosBrl: Coin,
    @SerializedName("etc_brl")
    val etcBrl: Coin,
    @SerializedName("eth_brl")
    val ethBrl: Coin,
    @SerializedName("gmr_brl")
    val gmrBrl: Coin,
    @SerializedName("gnt_brl")
    val gntBrl: Coin,
    @SerializedName("iop_brl")
    val iopBrl: Coin,
    @SerializedName("lcc_brl")
    val lccBrl: Coin,
    @SerializedName("ltc_brl")
    val ltcBrl: Coin,
    @SerializedName("mxt_brl")
    val mxtBrl: Coin,
    @SerializedName("nbr_brl")
    val nbrBrl: Coin,
    @SerializedName("omg_brl")
    val omgBrl: Coin,
    @SerializedName("onix_brl")
    val onixBrl: Coin,
    @SerializedName("paxg_brl")
    val paxgBrl: Coin,
    @SerializedName("smart_brl")
    val smartBrl: Coin,
    @SerializedName("sngls_brl")
    val snglsBrl: Coin,
    @SerializedName("trx_brl")
    val trxBrl: Coin,
    @SerializedName("tusd_brl")
    val tusdBrl: Coin,
    @SerializedName("usdt_brl")
    val usdtBrl: Coin,
    @SerializedName("xmr_brl")
    val xmrBrl: Coin,
    @SerializedName("xrp_brl")
    val xrpBrl: Coin,
    @SerializedName("zec_brl")
    val zecBrl: Coin,
    @SerializedName("zrx_brl")
    val zrxBrl: Coin
) {
    data class Coin(
        @SerializedName("active")
        val active: Int,
        @SerializedName("baseVolume")
        val baseVolume: Double,
        @SerializedName("baseVolume24")
        val baseVolume24: Double,
        @SerializedName("highestBid")
        val highestBid: Double,
        @SerializedName("highestBid24")
        val highestBid24: Double,
        @SerializedName("last")
        val last: Double,
        @SerializedName("lowestAsk")
        val lowestAsk: Double,
        @SerializedName("lowestAsk24")
        val lowestAsk24: Double,
        @SerializedName("market")
        val market: String,
        @SerializedName("percentChange")
        val percentChange: Double,
        @SerializedName("quoteVolume")
        val quoteVolume: Double,
        @SerializedName("quoteVolume24")
        val quoteVolume24: Double
    )

}