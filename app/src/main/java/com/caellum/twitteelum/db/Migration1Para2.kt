package com.caellum.twitteelum.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration1Para2 : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        val sql = "alter table Tweet add column foto text"
        database.execSQL(sql)
    }

}