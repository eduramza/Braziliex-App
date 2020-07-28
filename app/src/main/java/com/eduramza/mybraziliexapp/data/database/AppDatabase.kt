package com.eduramza.mybraziliexapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies

@Database(entities = [LocalCurrencies::class], version = 2)
abstract class AppDatabase: RoomDatabase(){

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "MyBraziliex-Database.db"
            ).fallbackToDestructiveMigration()
            .build()

    }
}