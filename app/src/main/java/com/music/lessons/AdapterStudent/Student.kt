package com.music.lessons.AdapterStudent

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "student")
data class Student (
    @PrimaryKey(autoGenerate = true) val id:Int,
    var name:String,
    var on:Boolean,
    var price:String,
    var number:Int,
    @SerializedName("count_lessons")var countLessons:Int,
    @SerializedName("time_lessons") var timeLessons:String,

    )


{

}