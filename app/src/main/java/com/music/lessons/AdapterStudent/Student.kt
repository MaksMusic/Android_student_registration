package com.music.lessons.AdapterStudent

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student")
data class Student (
    @PrimaryKey(autoGenerate = true) val id:Int,
    var name:String,
    var on:Boolean,
    var price:String,
    var number:Int,
    var countLessons:Int,
    var timeLessons:String,

    )


{

}