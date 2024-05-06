package com.music.lessons.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.music.lessons.AdapterStudent.Student


@Database(entities = [Student::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class) // Добавляем конвертер
abstract class DataBaseRoom : RoomDatabase() {

    abstract fun studentDao(): StudentDao

}