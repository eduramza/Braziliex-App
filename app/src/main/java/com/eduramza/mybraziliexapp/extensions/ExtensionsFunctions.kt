package com.eduramza.mybraziliexapp.extensions

import java.text.NumberFormat
import java.util.*

fun Double.convertDoubleToBRL(): String{
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Double.convertDoubleToBRLVol(): String{
    val ptBr = Locale("pt", "BR")
    return "${NumberFormat.getCurrencyInstance(ptBr).format(this)} (Vol.)"
}

fun Double.returnPercentWithSymbol() = "$this%"

fun String.getCryptoName() = Cryptos.valueOf(this.split("_")[0].toUpperCase()).coin