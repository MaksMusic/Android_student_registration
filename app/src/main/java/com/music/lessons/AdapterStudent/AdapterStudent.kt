package com.music.lessons.AdapterStudent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.lessons.databinding.ItemStudentBinding

class AdapterStudent(var studentList:ArrayList<Student> ):RecyclerView.Adapter<AdapterStudent.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setAddItem(student: Student) {
        studentList.add(student)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var itemStudentBinding: ItemStudentBinding)
        :RecyclerView.ViewHolder(itemStudentBinding.root){

            fun addStudent(student: Student){
                itemStudentBinding.nameStudent.text = student.name
                itemStudentBinding.timeLessons.text = student.time
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