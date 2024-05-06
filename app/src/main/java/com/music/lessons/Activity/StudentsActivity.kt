import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.music.lessons.AdapterStudent.Student
import com.music.lessons.R
import com.music.lessons.databinding.ActivityStudentsBinding
import com.music.lessons.room.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterStudent
    private lateinit var binding:ActivityStudentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterStudent(emptyList())
        recyclerView.adapter = adapter

        binding.addStudent.setOnClickListener(){
            var intent = Intent(this,AddStudentActivity::class.java)
            startActivity(intent)
        }
        loadDataFromDatabase()


    }

    private fun loadDataFromDatabase() {
        val studentDao = (application as App).getDataBase().studentDao()
        GlobalScope.launch(Dispatchers.IO) {
            val students = studentDao.getAllStudents()
            withContext(Dispatchers.Main) {
                adapter.studentList = students
                adapter.notifyDataSetChanged()
            }
        }
    }
}
