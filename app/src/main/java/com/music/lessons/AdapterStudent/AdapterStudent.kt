package com.music.lessons.AdapterStudent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.music.lessons.R
import com.music.lessons.databinding.ItemStudentBinding

class AdapterStudent(var studentList: ArrayList<Student>, var clickListener: OnClicItem) :
    RecyclerView.Adapter<AdapterStudent.ViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun setAddItem(student: Student) {
        studentList.add(student)
        notifyDataSetChanged()
    }

    fun sortStudents(sortList: ArrayList<Student>){
        studentList = sortList
        notifyDataSetChanged()

    }

    fun setData(sortList:ArrayList<Student>){
        studentList = sortList;
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
}