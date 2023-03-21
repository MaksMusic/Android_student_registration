package com.music.lessons.Activity

import android.app.TimePickerDialog
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
import java.util.*

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
        timeView()



    }
    private fun timeView(){
        binding.day1TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day1TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }

        binding.day2TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day2TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }


        binding.day3TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day3TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }

        binding.day4TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day4TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }

        binding.day5TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day5TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }

        binding.day6TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day6TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }

        binding.day7TimeEdittext.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.day7TimeEdittext.setText(time)
                }, hour, minute, true)
            timePickerDialog.show()
        }


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