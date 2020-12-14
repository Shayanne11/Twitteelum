package com.caellum.twitteelum.repository

import androidx.lifecycle.LiveData
import com.caellum.twitteelum.db.TweetDao
import com.caellum.twitteelum.modelo.Tweet

class TweetRepository (private val fonteDeDados:TweetDao){


    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)

    fun busca(): LiveData<List<Tweet>> = fonteDeDados.buscarTodos()

    fun deleta(tweet: Tweet) = fonteDeDados.deleta(tweet)

}

