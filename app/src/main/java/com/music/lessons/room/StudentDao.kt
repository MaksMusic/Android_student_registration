package com.music.lessons.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.music.lessons.AdapterStudent.Student

@Dao
interface StudentDao {

    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)


    @Query("SELECT* FROM student")
    suspend fun getAllStudents(): List<Student>

//    @Query("SELECT * FROM student")//получить весь список
//    fun getAllStudents(): LiveData<List<Student>>

   // @Query("UPDATE student SET lastName = :lastName WHERE id =:id")
    //suspend fun updateId(lastName: String, id: Long)

    @Query("SELECT * FROM student WHERE id = :id") //получить по id
    suspend fun getUserId(id: Long): Student

}