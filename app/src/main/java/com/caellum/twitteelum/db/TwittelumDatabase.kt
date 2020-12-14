package com.caellum.twitteelum.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.caellum.twitteelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 2)
abstract class TwittelumDatabase : RoomDatabase (){

    abstract fun getTweetDao(): TweetDao

    companion object {

        private var database: TwittelumDatabase? = null

        fun getDatabase(context: Context): TwittelumDatabase{
            return database?: criaDatabase(context).also { database =it }
        }

        private fun criaDatabase(context:Context): TwittelumDatabase {
            return Room.databaseBuilder(
                context,
                TwittelumDatabase::class.java,
                "twittelum-bd").allowMainThreadQueries().addMigrations(Migration1Para2).build()

        }
    }
}// singleton - design pattern -- estatico -- instancia unica == object