import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.music.lessons.room.App
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.room.StudentDao
import com.music.lessons.databinding.ActivityAddStudentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var studentDao: StudentDao
    private var selectedDateTime: LocalDateTime? = null // Переменная для хранения выбранной даты и времени

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем экземпляр базы данных
        val app = application as App
        studentDao = app.getDataBase().studentDao()

        // Обработчик нажатия на кнопку "Добавить"
        binding.btnAdd.setOnClickListener {
            addStudentToDatabase()
        }

        // Обработчик нажатия на кнопку "Выбрать дату и время"
        binding.buttonSelectDateTime.setOnClickListener {
            showDateTimePicker()
        }
    }

    // Метод для отображения диалога выбора даты и времени
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDateTimePicker() {
        val currentDateTime = LocalDateTime.now()
        val year = currentDateTime.year
        val month = currentDateTime.monthValue - 1 // Month is 0-based in DatePickerDialog
        val day = currentDateTime.dayOfMonth

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)

                val initialHour = currentDateTime.hour
                val initialMinute = currentDateTime.minute

                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        selectedDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
                        binding.textViewSelectedDateTime.text = selectedDateTime.toString() // Отображаем выбранную дату и время
                    },
                    initialHour,
                    initialMinute,
                    true
                )

                timePickerDialog.show()
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = currentDateTime.toEpochSecond(ZoneOffset.UTC) * 1000 // Устанавливаем минимальную дату выбора (текущая дата)
        datePickerDialog.show()
    }

    // Метод для добавления студента в базу данных
    private fun addStudentToDatabase() {
        val name = binding.editTextName.text.toString()
        val type = binding.editTextType.text.toString()
        val price = binding.editTextPrice.text.toString().toDoubleOrNull() ?: 0.0
        val isActive = binding.switchOn.isChecked

        // Проверяем, выбрана ли дата и время
        if (selectedDateTime == null) {
            // Если дата и время не выбраны, вы можете показать сообщение об ошибке или просто вернуться
            return
        }

        val student = Student(0, name, type, price, isActive, selectedDateTime!!)

        // Запускаем корутину для вставки студента в базу данных
        CoroutineScope(Dispatchers.IO).launch {
            studentDao.insert(student)
            finish() // Закрываем активити после успешной вставки
        }
    }
}
