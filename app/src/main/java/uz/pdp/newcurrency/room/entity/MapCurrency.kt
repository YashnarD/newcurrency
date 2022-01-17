package uz.pdp.newcurrency.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MapCurrency (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val date: String,
    val cell: String,
    val buy: String
    )
    : Serializable