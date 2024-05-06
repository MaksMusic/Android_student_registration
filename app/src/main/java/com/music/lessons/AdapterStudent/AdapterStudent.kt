import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.music.lessons.AdapterStudent.Student
import com.music.lessons.R

class AdapterStudent(var studentList: List<Student>) : RecyclerView.Adapter<AdapterStudent.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val isActiveTextView: TextView = itemView.findViewById(R.id.isActiveTextView)
        val dateTimeTextView: TextView = itemView.findViewById(R.id.dateTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = studentList[position]

        holder.nameTextView.text = currentItem.name
        holder.typeTextView.text = currentItem.type
        holder.priceTextView.text = currentItem.price.toString()
        holder.isActiveTextView.text = if (currentItem.isActive) "Active" else "Inactive"
        holder.dateTimeTextView.text = currentItem.dateTime.toString()
    }

    override fun getItemCount() = studentList.size
}
