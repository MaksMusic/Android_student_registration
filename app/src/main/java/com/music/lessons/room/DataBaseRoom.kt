package com.music.lessons.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.music.lessons.AdapterStudent.Student

@Database(entities = [Student::class], version = 1)
abstract class DataBaseRoom : RoomDatabase() {

    abstract fun studentDao(): StudentDao

//    companion object {
//        @Volatile
//        private var INSTANCE: DataBaseRoom? = null
//
//        fun getInstance(context: Context): DataBaseRoom {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DataBaseRoom::class.java,
//                    "my_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}