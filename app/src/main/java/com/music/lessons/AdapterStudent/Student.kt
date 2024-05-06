package com.music.lessons.AdapterStudent

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: String,
    val price: Double,
    val isActive: Boolean,
    val dateTime: LocalDateTime // Добавляем поле для хранения даты и времени
)
