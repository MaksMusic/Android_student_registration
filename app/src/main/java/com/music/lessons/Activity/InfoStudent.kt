package com.music.lessons.Activity

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.music.lessons.AdapterStudent.AdapterStudent
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.R
import com.music.lessons.databinding.ActivityInfoStudentBinding
import com.music.lessons.databinding.ActivityStudentsBinding
import com.music.lessons.room.App
import com.music.lessons.room.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class InfoStudent : AppCompatActivity() {

    lateinit var binding: ActivityInfoStudentBinding
    private var id: Long? = null
    private lateinit var studentDao: StudentDao

   // lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
       studentDao = (application as App).getDataBase().studentDao()
        id = intent.extras?.getLong("id")



        initText()
        red()
        delite()
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


    private fun delite() {
        binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Вы действительно хотите удалить данные?")
            builder.setCancelable(true)
            builder.setPositiveButton("Да") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    var student = studentDao.getUserId(id!!.toLong());
                    studentDao.delete(student)

                    val intent = Intent(this@InfoStudent, StudentsActivity::class.java)
                    startActivity(intent)
                    finish()
                    withContext(Dispatchers.Main) {

                    }

                }
            }
            builder.setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()

            }
            val dialog = builder.create()
            dialog.show()
        }
    }


    fun planning(): String {
        var str = ""
        if (binding.day1Checkbox.isChecked) str += "пн ${binding.day1TimeEdittext.text} , "
        if (binding.day2Checkbox.isChecked) str += "вт ${binding.day2TimeEdittext.text} , "
        if (binding.day3Checkbox.isChecked) str += "ср ${binding.day3TimeEdittext.text} , "
        if (binding.day4Checkbox.isChecked) str += "чт ${binding.day4TimeEdittext.text} , "
        if (binding.day5Checkbox.isChecked) str += "пт ${binding.day5TimeEdittext.text} , "
        if (binding.day6Checkbox.isChecked) str += "сб ${binding.day6TimeEdittext.text} , "
        if (binding.day7Checkbox.isChecked) str += "вс ${binding.day7TimeEdittext.text} , "
        return str;
    }

    fun red() {
        binding.btnRed.setOnClickListener() {
            var on = binding.switchOn.isChecked
            var name = binding.editTextName.text.toString() ?: ""
            var price = binding.editTextPrice.text.toString() ?: ""
            var timeLesson = planning() ?: ""
            var countLesson = binding.editCountLesson.text.toString()


            var student = Student(
                id = id?.toInt() ?: 0 ,
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
                studentDao.update(student)


                withContext(Dispatchers.Main) {
                    finish()
                    var intent = Intent(applicationContext, StudentsActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }


        }
    }


    fun initText() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (id != null) {
                var student = studentDao.getUserId(id!!) ?: null
                if (student != null){
                    var (id,name, on, price, number, countLessons, timeLessons) = student

                    withContext(Dispatchers.Main) {
                        if (countLessons ==0){
                            binding.editCountLesson.setText("")
                        }else{
                            binding.editCountLesson.setText(student.countLessons.toString())
                        }


                        binding.editTextName.setText(name)
                        binding.editTextPrice.setText(price)

                        if (on) {
                            binding.switchOn.isChecked = true
                            binding.switchOn.text = "ученик активен"
                        }else{
                            binding.switchOn.isChecked = false
                            binding.switchOn.text = "ученик не активен"
                        }


                        var timeLesson = student.timeLessons.split(",")

                        for (s in timeLesson) {
                           // Log.e("SPLIT", s)
                            if (s.lowercase().contains("пн")) {
                               // Log.e("PN", true.toString())
                                binding.day1Checkbox.isChecked = true
                                binding.day1TimeEdittext.setText(regexPutTime(s))
                                println(s)
                                println(s.replace("\\D",""))
                            }
                            if (s.lowercase().contains("вт")) {
                                binding.day2Checkbox.isChecked = true
                                binding.day2TimeEdittext.setText(regexPutTime(s))
                            }
                            if (s.lowercase().contains("ср")) {
                                binding.day3Checkbox.isChecked = true
                                binding.day3TimeEdittext.setText(regexPutTime(s))
                            }
                            if (s.lowercase().contains("чт")) {
                                binding.day4Checkbox.isChecked = true
                                binding.day4TimeEdittext.setText(regexPutTime(s))
                            }
                            if (s.lowercase().contains("пт")) {
                                binding.day5Checkbox.isChecked = true
                                binding.day5TimeEdittext.setText(regexPutTime(s))
                            }
                            if (s.lowercase().contains("сб")) {
                                binding.day6Checkbox.isChecked = true
                                binding.day6TimeEdittext.setText(regexPutTime(s))
                            }
                            if (s.lowercase().contains("вс")) {
                                binding.day7Checkbox.isChecked = true
                                binding.day7TimeEdittext.setText(regexPutTime(s))
                            }
                        }



                    }
                }

            }


        }


    }

    fun regexPutTime(s:String) : String{
        return s.replace(Regex("[^\\d\\s]|(\\d{2})[^\\d]|\\s(\\d{1,2})[^\\d]"), "$1$2 ").trim()
    }

}