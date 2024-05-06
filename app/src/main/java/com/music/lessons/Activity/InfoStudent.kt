import android.annotation.SuppressLint

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.databinding.ActivityInfoStudentBinding
import com.music.lessons.room.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class InfoStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoStudentBinding
    private var studentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentId = intent.getLongExtra("student_id", 0)

        loadStudentInfo(studentId)

        setupButtons()
    }

    private fun loadStudentInfo(studentId: Long) {
        val studentDao = (application as App).getDataBase().studentDao()
        lifecycleScope.launch(Dispatchers.IO) {
            val student = studentDao.getUserId(studentId)
            withContext(Dispatchers.Main) {
                student?.let {
                    binding.editTextName.setText(it.name)
                    binding.editTextType.setText(it.type)
                    binding.editTextPrice.setText(it.price.toString())
                    binding.switchOn.isChecked = it.isActive
                    // Преобразование LocalDateTime в строку для отображения
                    val dateTimeString = it.dateTime.toString()
                    binding.textViewSelectedDateTime.text = dateTimeString
                }
            }
        }
    }

    private fun setupButtons() {
        binding.buttonSelectDateTime.setOnClickListener {
            // TODO: Реализовать логику выбора даты и времени
            Toast.makeText(this, "Выбор даты и времени", Toast.LENGTH_SHORT).show()
        }

        binding.btnRed.setOnClickListener {
            updateStudent()
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }


    @SuppressLint("NewApi")
    private fun updateStudent() {
        val name = binding.editTextName.text.toString()
        val type = binding.editTextType.text.toString()
        val price = binding.editTextPrice.text.toString().toDouble()
        val isActive = binding.switchOn.isChecked
        val dateTime = LocalDateTime.now() // Просто для примера, нужно заменить на реальное значение

        val updatedStudent = Student(studentId, name, type, price, isActive, dateTime)
        val studentDao = (application as App).getDataBase().studentDao()
        lifecycleScope.launch(Dispatchers.IO) {
            studentDao.update(updatedStudent)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@InfoStudentActivity, "Данные студента обновлены", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Вы действительно хотите удалить данные?")
            .setCancelable(true)
            .setPositiveButton("Да") { _, _ ->
                deleteStudent()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteStudent() {
        val studentDao = (application as App).getDataBase().studentDao()
        lifecycleScope.launch(Dispatchers.IO) {
          //  studentDao.deleteStudentById(studentId)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@InfoStudentActivity, "Данные студента удалены", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
