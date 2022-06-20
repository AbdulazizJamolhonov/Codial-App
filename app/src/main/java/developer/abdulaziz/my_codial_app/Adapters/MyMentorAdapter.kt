package developer.abdulaziz.my_codial_app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.my_codial_app.Classes.Mentor
import developer.abdulaziz.my_codial_app.databinding.ItemAddMentorBinding

class MyMentorAdapter(
    private var list: ArrayList<Mentor>,
    private var onItemClickListener: OnMenuClickListener
) :
    RecyclerView.Adapter<MyMentorAdapter.ViewHolderMenu>() {

    inner class ViewHolderMenu(private val binding: ItemAddMentorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(mentor: Mentor, position: Int) {
            binding.apply {
                mentorName.text = "${mentor.name} ${mentor.surname}"
                editMentor.setOnClickListener { onItemClickListener.onEdit(mentor, position) }
                deleteMentor.setOnClickListener { onItemClickListener.onDelete(mentor, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMenu {
        val bin = ItemAddMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderMenu(bin)
    }

    override fun onBindViewHolder(holder: ViewHolderMenu, position: Int) =
        holder.onBind(list[position], position)

    override fun getItemCount(): Int = list.size

    interface OnMenuClickListener {
        fun onEdit(mentor: Mentor, position: Int)
        fun onDelete(mentor: Mentor, position: Int)
    }
}

