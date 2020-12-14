package com.caellum.twitteelum.app

import android.app.Application

class TwittellumAplication:Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{

        lateinit var application: TwittellumAplication
        fun getInstance(): TwittellumAplication = application
    }
}