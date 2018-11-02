package inkant1990.com.cleanhomes.presentation.screen.student.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inkant1990.com.cleanhomes.R
import inkant1990.com.domain.entity.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentListAdapter() :
    RecyclerView.Adapter<StudentListAdapter.Holder>() {
    var items: List<Student> = emptyList()
    lateinit var listener: OnItemClickListener
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_student, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount() = items.size


    fun setStudents(student: List<Student>) {
        items = student
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onClick(items[position].id!!)
        }
        holder.bind(items[position])
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(id: String)
    }


    inner class Holder(val binding: View) : RecyclerView.ViewHolder(binding) {

        fun bind(item: Student) {
            binding.tvHome10Name.text = item.name

        }
    }
}
