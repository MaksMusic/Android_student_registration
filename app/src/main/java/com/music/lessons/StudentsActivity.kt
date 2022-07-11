package com.music.lessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.music.lessons.AdapterStudent.AdapterStudent
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.databinding.ActivityMainBinding
import com.music.lessons.databinding.ActivityStudentsBinding

class StudentsActivity : AppCompatActivity() {
    val studentsList:ArrayList<Student> = arrayListOf()
    val adapterStudents = AdapterStudent(studentsList)
    lateinit var binding: ActivityStudentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapterStudents



        binding.addStudent.setOnClickListener(){
            adapterStudents.setAddItem(Student("Name","10 20 30",false))

        }
    }
}