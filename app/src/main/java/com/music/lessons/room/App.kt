package com.music.lessons.room

import android.app.Application

import androidx.room.Room

class App : Application() {

    lateinit var database:DataBaseRoom

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext, DataBaseRoom::class.java, "my_database")
            .build()
    }
    fun getDataBase():DataBaseRoom{
        return database
    }
}

