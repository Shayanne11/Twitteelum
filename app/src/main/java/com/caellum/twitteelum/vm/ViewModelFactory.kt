package com.caellum.twitteelum.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caellum.twitteelum.app.TwittellumAplication
import com.caellum.twitteelum.db.TwittelumDatabase
import com.caellum.twitteelum.repository.TweetRepository

object ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val contexto = TwittellumAplication.getInstance()
        val database = TwittelumDatabase.getDatabase(contexto)
        val tweetDao = database.getTweetDao()
        val repository = TweetRepository(tweetDao)
        return TweetViewModel(repository) as T
    }
}