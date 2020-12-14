package com.caellum.twitteelum.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tweet(
    val conteudo: String,
    val foto : String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
){

}