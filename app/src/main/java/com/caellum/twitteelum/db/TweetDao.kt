package com.caellum.twitteelum.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.caellum.twitteelum.modelo.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salva(tweet:Tweet)

    @Query("select * from Tweet ")
    fun buscarTodos() : LiveData<List<Tweet>>

    @Delete
    fun deleta (tweet: Tweet)

}