package com.eduramza.mybraziliexapp.extensions

import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*


// ************************* Double **********************
fun Double.convertDoubleToBRL(): String{
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Double.convertDoubleToBRLVol(): String{
    val ptBr = Locale("pt", "BR")
    return "${NumberFormat.getCurrencyInstance(ptBr).format(this)} (Vol.)"
}

fun Double.returnPercentWithSymbol() = "$this%"

fun Double.changeDotInComma() = this.toString().replace(".", ",")

//*************** String ******************
fun String.getCryptoName() = CurrenciesEnum.valueOf(this.split("_")[0].toUpperCase()).coin

fun String.toCommaDouble() : Double{
    return try {
        this.replace(",", ".").toDouble()
    } catch (e: NumberFormatException){
        this.toDouble()
    }
}

