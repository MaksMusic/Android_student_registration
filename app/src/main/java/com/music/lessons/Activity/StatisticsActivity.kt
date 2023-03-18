package com.music.lessons.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.music.lessons.AdapterStudent.AdapterStudent
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.databinding.ActivityStatisticsBinding
import com.music.lessons.databinding.ActivityStudentsBinding
import com.music.lessons.room.App
import com.music.lessons.room.StudentDao

class StatisticsActivity : AppCompatActivity() {
    lateinit var adapterStudents: AdapterStudent
    lateinit var binding: ActivityStatisticsBinding
    var listPassRoom = ArrayList<Student>()
    private lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myToolbar.title = ""
        setSupportActionBar(binding.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentDao = (application as App).getDataBase().studentDao()



    }
    // Показываем кнопку в ActionBar с иконкой стрелки назад


    // Обработчик нажатия на кнопку в ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Закрываем текущее Activity и переходим на другое
                val intent = Intent(this, StudentsActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


