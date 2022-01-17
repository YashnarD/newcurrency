package uz.pdp.newcurrency.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.pdp.newcurrency.room.dao.CurrencyDao
import uz.pdp.newcurrency.room.entity.MapCurrency

@Database(entities = [MapCurrency::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

abstract fun currencyDao(): CurrencyDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase{
            if(instance == null)
            {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}