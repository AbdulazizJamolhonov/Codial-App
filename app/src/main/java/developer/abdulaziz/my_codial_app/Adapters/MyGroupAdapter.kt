package developer.abdulaziz.my_codial_app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Classes.Talaba
import developer.abdulaziz.my_codial_app.databinding.ItemShowGroupBinding

class MyGroupAdapter(
    private var listGroup: ArrayList<Group>,
    private var listTalaba: ArrayList<Talaba>,
    private var onItemClickListener: OnMenuClickListener
) :
    RecyclerView.Adapter<MyGroupAdapter.ViewHolderGroup>() {

    inner class ViewHolderGroup(private val binding: ItemShowGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(group: Group, position: Int) {
            binding.apply {
                groupName.text = group.name
                groupCount.text = "O’quvchilar soni: ${listTalaba.size} ta"
                showGroup.setOnClickListener { onItemClickListener.onShow(group, position) }
                editGroup.setOnClickListener { onItemClickListener.onEdit(group, position) }
                deleteGroup.setOnClickListener { onItemClickListener.onDelete(group, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGroup {
        val bin = ItemShowGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderGroup(bin)
    }

    override fun onBindViewHolder(holder: ViewHolderGroup, position: Int) =
        holder.onBind(listGroup[position], position)

    override fun getItemCount(): Int = listGroup.size

    interface OnMenuClickListener {
        fun onShow(group: Group, position: Int)
        fun onEdit(group: Group, position: Int)
        fun onDelete(group: Group, position: Int)
    }
}