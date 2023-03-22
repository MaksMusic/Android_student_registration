package com.music.lessons.AdapterStudent

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.music.lessons.R
import com.music.lessons.databinding.ItemStudentBinding
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class AdapterStudent(var studentList: ArrayList<Student>, var clickListener: OnClicItem) :
    RecyclerView.Adapter<AdapterStudent.ViewHolder>() {
    private var green:Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    fun setAddItem(student: Student) {
        studentList.add(student)
        notifyDataSetChanged()
    }

    fun sortStudents(sortList: ArrayList<Student>){
        studentList = sortList
        green = false
        notifyDataSetChanged()

    }

    //
    fun setData(sortList:ArrayList<Student>){
        studentList = sortList;
        green = true
        notifyDataSetChanged()
    }

    interface OnClicItem {
        fun clicItem(id: Long)
    }


    inner class ViewHolder(var itemStudentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemStudentBinding.root) {

        fun addStudent(student: Student) {
            itemStudentBinding.nameStudent.text = student.name ?: ""
            itemStudentBinding.timeLessons.text = student.timeLessons ?: ""
            itemStudentBinding.price.text = student.price ?: ""
            itemStudentBinding.number.text = student.name ?: ""

            if (student.countLessons == 0) {
                itemStudentBinding.countLessons.text = ""
            } else {
                itemStudentBinding.countLessons.text = student.countLessons.toString() ?: ""
            }
            itemStudentBinding.number.text = student.id.toString() ?: ""

            if (student.on) itemStudentBinding.LL.setBackgroundResource(R.drawable.border)
            if (!student.on) itemStudentBinding.LL.setBackgroundResource(R.drawable.borderoff)

            if (findAndCompareTimeWithCalendar(student.timeLessons.uppercase(),getCurrentDayOfWeek()) && green==true){
                itemStudentBinding.LL.setBackgroundResource(R.drawable.border_green)
            }


            itemView.setOnClickListener() {
                clickListener.clicItem(student.id.toLong() ?: 1)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addStudent(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    private fun getCurrentDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val dayNames = arrayOf("ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ")

        return dayNames[dayOfWeek - 1]
    }




    fun findAndCompareTimeWithCalendar(timeLessons: String, date: String): Boolean {

        val timeLessons = timeLessons.split(",")
        val regex = Regex("(?<=$date[.:])(\\d+)(?=,)")

        for (lesson in timeLessons) {
            if (lesson.contains(date) && date == getCurrentDayOfWeek()){
                val currentTime =getCurrentTimeFormatted().toInt()
                    //"${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}${Calendar.getInstance().get(Calendar.MINUTE)}".toInt()

                var time = lesson.replace(Regex("[^\\d]"), "")
                if (time.isBlank()) time = "0" else time = time

                Log.e("TIME1",time.toString())
                Log.e("TIME2",currentTime.toString())
                return time.toInt() < currentTime
            }

        }
        return false
    }



    fun getCurrentTimeFormatted() : String {
        val currentTime = Calendar.getInstance()
        val hours = currentTime.get(Calendar.HOUR_OF_DAY)
        val minutes = currentTime.get(Calendar.MINUTE)
        return String.format("%02d%02d", hours, minutes)
    }
}












