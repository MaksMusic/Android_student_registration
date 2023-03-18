package com.music.lessons.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.databinding.ActivityAddStudentBinding
import com.music.lessons.room.App
import com.music.lessons.room.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddStudentActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddStudentBinding
    private lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentDao = (application as App).getDataBase().studentDao()

        init()

        add()


    }


    private fun init() {
        lifecycleScope.launch(Dispatchers.IO) {


        }
    }



    fun planning(): String {
        var str = ""
        if (binding.day1Checkbox.isChecked) str += "пн " + binding.day1TimeEdittext.text + " , "
        if (binding.day2Checkbox.isChecked) str += "вт " + binding.day2TimeEdittext.text + " , "
        if (binding.day3Checkbox.isChecked) str += "ср " + binding.day3TimeEdittext.text + " , "
        if (binding.day4Checkbox.isChecked) str += "чт " + binding.day4TimeEdittext.text + " , "
        if (binding.day5Checkbox.isChecked) str += "пт " + binding.day5TimeEdittext.text + " , "
        if (binding.day6Checkbox.isChecked) str += "сб " + binding.day6TimeEdittext.text + " , "
        if (binding.day7Checkbox.isChecked) str += "вс " + binding.day7TimeEdittext.text + " , "
        return str;
    }


    fun add() {
        binding.btnAdd.setOnClickListener() {
            var on = binding.switchOn.isChecked
            var name = binding.editTextName.text.toString() ?: ""
            var price = binding.editTextPrice.text.toString() ?: ""
            var timeLesson = planning() ?: ""
            var countLesson = binding.editCountLesson.text ?: ""


            var student: Student = Student(
                id = 0,
                on = on,
                name = name,
                price = price,
                timeLessons = timeLesson,
                countLessons = if(countLesson.isEmpty()){
                    0
                }else{
                    Log.e("replase",countLesson.toString())
                    countLesson.replace(Regex("[^\\d]"), "").toInt() ?: 0

                     },

                number = 1
            )

            lifecycleScope.launch(Dispatchers.IO) {
                studentDao.insert(student)

                withContext(Dispatchers.Main) {
                    finish()
                    var intent = Intent(applicationContext, StudentsActivity::class.java)
                    startActivity(intent)

                }
            }


        }
    }
}