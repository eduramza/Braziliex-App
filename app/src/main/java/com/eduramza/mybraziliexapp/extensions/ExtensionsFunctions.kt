package com.eduramza.mybraziliexapp.extensions

import java.text.NumberFormat
import java.util.*

fun Double.convertDoubleToBRL(): String{
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Double.returnPercentWithSymbol(): String{
    return "$this %"
}