package com.caellum.twitteelum.vm

import androidx.lifecycle.ViewModel
import com.caellum.twitteelum.modelo.Tweet
import com.caellum.twitteelum.repository.TweetRepository

class TweetViewModel( private val repository: TweetRepository):ViewModel() {
    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun busca() = repository.busca()

    fun deleta(tweet: Tweet) = repository.deleta(tweet)

}