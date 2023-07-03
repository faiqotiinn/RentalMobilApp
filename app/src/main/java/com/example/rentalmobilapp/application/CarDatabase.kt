package com.example.rentalmobilapp.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rentalmobilapp.dao.CarDao
import com.example.rentalmobilapp.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase: RoomDatabase() {
    abstract fun CarDao(): CarDao

    companion object{
        private var INSTANCE: CarDatabase? = null

        fun getDatabase (context: Context): CarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                   context.applicationContext,
                   CarDatabase::class.java,
                   "car_database_88"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}