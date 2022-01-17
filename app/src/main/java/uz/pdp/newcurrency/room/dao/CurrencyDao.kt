package uz.pdp.newcurrency.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import uz.pdp.newcurrency.room.entity.MapCurrency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrency(mapCurrency: MapCurrency)

    @Query("select * from MapCurrency")
    fun getAllCurrency(): List<MapCurrency>
}