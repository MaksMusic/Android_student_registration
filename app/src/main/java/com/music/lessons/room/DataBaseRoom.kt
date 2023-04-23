package com.music.lessons.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.music.lessons.AdapterStudent.Student

@Database(entities = [Student::class], version = 2)
abstract class DataBaseRoom : RoomDatabase() {

    abstract fun studentDao(): StudentDao

}