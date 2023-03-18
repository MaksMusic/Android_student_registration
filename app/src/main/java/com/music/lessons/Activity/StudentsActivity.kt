package com.music.lessons.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.music.lessons.AdapterStudent.AdapterStudent
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.R
import com.music.lessons.databinding.ActivityStatisticsBinding
import com.music.lessons.databinding.ActivityStudentsBinding
import com.music.lessons.room.App
import com.music.lessons.room.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsActivity : AppCompatActivity(), AdapterStudent.OnClicItem {


    lateinit var adapterStudents: AdapterStudent
    lateinit var binding: ActivityStudentsBinding
    var listPassRoom = ArrayList<Student>()
    private lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myToolbar.title = ""
        setSupportActionBar(binding.myToolbar)

        studentDao = (application as App).getDataBase().studentDao()

        init()
        addStudent()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all_students -> {
                var sortAllList = listPassRoom.sortedByDescending { it.on }
                var arraListSortMonday = ArrayList<Student>(sortAllList)
                adapterStudents.setData(arraListSortMonday)
                setDayToolBar("список",sortAllList.size)
                true
            }
            R.id.monday -> {
                sortList("пн")
                true
            }
            R.id.tuesday -> {
                sortList("вт")
                true
            }
            R.id.wednesday -> {
                sortList("ср")
                true
            }
            R.id.thursday -> {
                sortList("чт")
                true
            }
            R.id.friday -> {
                sortList("пт")
                true
            }
            R.id.saturday -> {
                sortList("сб")
                true
            }
            R.id.sunday -> {
                sortList("вс")
                true
            }
            R.id.statistics -> {
                statistica()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun clicItem(id: Long) {
        lifecycleScope.launch(Dispatchers.IO) {
            var intent1 = Intent(this@StudentsActivity, InfoStudent::class.java)
            intent1.putExtra("id", id)
            startActivity(intent1)
            finish()


        }
    }


    private fun init() {
        lifecycleScope.launch(Dispatchers.IO) {

            var studentsList = studentDao.getAllStudents()
            studentsList = studentsList.sortedByDescending { it.on }

            println(studentsList)
            if (studentsList != null) {
                listPassRoom.addAll(studentsList)
            } else {

            }

            withContext(Dispatchers.Main) {

                adapterStudents = AdapterStudent(listPassRoom, this@StudentsActivity)
                binding.recyclerview.adapter = adapterStudents
                setDayToolBar("список",listPassRoom.size)
            }
        }
    }

    private fun addStudent() {
        binding.addStudent.setOnClickListener() {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
            finish()

        }
    }


    private fun sortList(day:String){
        val sortedList =
            listPassRoom.filter { it.on && it.timeLessons.contains(day, true) }
                .sortedWith(compareBy(
                    {
                        val digits = it.timeLessons.substringAfter("$day ", "")
                            .substringBefore(" ", "")
                            .take(2)
                        digits.toIntOrNull() ?: Int.MAX_VALUE
                    }
                ))

        var arraListSort = ArrayList<Student>(sortedList)
        // Устанавливаем новый список в адаптер
        adapterStudents.setData(arraListSort)
        setDayToolBar(day,arraListSort.size)

    }

    fun setDayToolBar(day:String, count:Int){
        when (day){
            "пн"-> binding.textToolBar.setText("Понидельник")
            "вт"-> binding.textToolBar.setText("Вторник")
            "ср"-> binding.textToolBar.setText("Среда")
            "чт"-> binding.textToolBar.setText("Четверг")
            "пт"-> binding.textToolBar.setText("Пятница")
            "сб"-> binding.textToolBar.setText("Cуббота")
            "вс"-> binding.textToolBar.setText("Воскресенье")
            "список"-> binding.textToolBar.setText("Список учеников")
        }
        binding.textToolBar.setText("${binding.textToolBar.text}  $count" )


    }



    private fun statistica(){
        var intent = Intent(this,StatisticsActivity::class.java)
        startActivity(intent)
        finish()


    }



}