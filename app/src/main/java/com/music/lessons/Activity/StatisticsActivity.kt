package com.music.lessons.Activity


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.databinding.ActivityStatisticsBinding
import com.music.lessons.room.App
import com.music.lessons.room.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class StatisticsActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE = 1
    }

    lateinit var binding: ActivityStatisticsBinding
    lateinit var listPassRoom: ArrayList<Student>
    private lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myToolbar.title = ""
        setSupportActionBar(binding.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentDao = (application as App).getDataBase().studentDao()
       // init()
        //json()
        //readStudentsFromJson()

    }


//    fun init() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            listPassRoom = ArrayList(studentDao.getAllStudents())
//
//            withContext(Dispatchers.IO) {
//                binding.allStudentsCountTextView.setText(
//                    "${binding.allStudentsCountTextView.text} ${countActiveStudents(listPassRoom)}"
//                )
//
//                binding.activeStudentsCountTextView.setText(
//                    "${binding.activeStudentsCountTextView.text} ${listPassRoom.size}"
//                )
//                binding.dayRevenueTextView.setText("${binding.dayRevenueTextView.text} ${incomePerDay()}")
//                binding.weeklyRevenueTextView.setText("${binding.weeklyRevenueTextView.text} ${incomePerWeek()}")
//                binding.TextViewPaidLesson.let{it.setText("${it.text} ${incomePairLesson()}") }
//
//            }
//        }
//    }
//
//    fun countActiveStudents(students: ArrayList<Student>): Int {
//        var count = 0
//        for (student in students) {
//            if (student.on) {
//                count++
//            }
//        }
//        return count
//    }
//
//
//    // Показываем кнопку в ActionBar с иконкой стрелки назад
//
//
//    // Обработчик нажатия на кнопку в ActionBar
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                // Закрываем текущее Activity и переходим на другое
//                val intent = Intent(this, com.music.lessons.Activity.StudentsActivity::class.java)
//                startActivity(intent)
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//
//    private fun incomePerDay(): Int {
//        var sum = 0.0;
//        for (student in listPassRoom) {
//            if (student.timeLessons.uppercase().contains(getCurrentDayOfWeek())) {
//                if (!student.price.isBlank()) {
//                    if (student.on)
//                        sum += student.price.toDouble()
//                }
//
//            }
//        }
//        return sum.toInt()
//    }
//
//
//    private fun incomePairLesson(): Int {
//        var sum = 0;
//        for (student in listPassRoom) {
//            if (!student.price.isBlank()) {
//                if (student.countLessons > 0)
//                    sum += student.price.toInt() * student.countLessons
//            }
//        }
//        return sum.toInt()
//    }
//
//    private fun incomePerWeek(): Int {
//        var sum = 0.0;
//        for (student in listPassRoom) {
//            if (!student.price.isBlank()) {
//                if (student.on)
//                    sum += student.price.toDouble()
//            }
//        }
//        return sum.toInt()
//    }
//
//
//    private fun getCurrentDayOfWeek(): String {
//        val calendar = Calendar.getInstance()
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
//
//        val dayNames = arrayOf("ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ")
//
//        return dayNames[dayOfWeek - 1]
//    }
//
//    private fun json() {
//        binding.saveToJsonButton.setOnClickListener {
//
//            // Сериализуем объект в строку JSON
//            val json = Gson().toJson(listPassRoom)
//
//            // Сохраняем JSON в файл во внешнем хранилище
//            val file = File(getExternalFilesDir(null), "students.json")
//            file.writeText(json)
//
//            // Создаем URI для передачи файла через FileProvider
//            val uri = FileProvider.getUriForFile(this, "com.example.lesson.fileprovider", file)
//
//            // Создаем Intent для отправки файла через Telegram
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.type = "*/*"
//            intent.putExtra(Intent.EXTRA_STREAM, uri)
//            intent.setPackage("org.telegram.messenger")
//
//            // Добавляем флаг, чтобы разрешить чтение URI другим приложениям
//            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//
//            // Запускаем Intent
//            startActivity(Intent.createChooser(intent, "Отправить файл"))
//        }
//    }
//
//
//    fun readStudentsFromJson() {
//        binding.importoJsonButton.setOnClickListener() {
//            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
//                type = "application/json"
//            }
//            startActivityForResult(intent, REQUEST_CODE)
//        }
//
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            data.data?.let { uri ->
//                val inputStream = contentResolver.openInputStream(uri)
//                val jsonString = inputStream?.bufferedReader().use { it?.readText() } ?: ""
//                val students = Gson().fromJson(jsonString, Array<Student>::class.java).toList()
//
//                lifecycleScope.launch(Dispatchers.IO) {
//                    for (student in students) {
//                        studentDao.insert(student)
//                    }
//                }
//
//
//            }
//        }
//    }


}




