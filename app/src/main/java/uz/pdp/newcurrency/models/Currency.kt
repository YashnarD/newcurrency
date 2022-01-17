package uz.pdp.newcurrency.models

import java.io.Serializable

data class Currency(
    val cb_price: String,
    val code: String,
    val date: String,
    val nbu_buy_price: String,
    val nbu_cell_price: String,
    val title: String
) : Serializable