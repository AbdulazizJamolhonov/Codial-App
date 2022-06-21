package developer.abdulaziz.my_codial_app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.my_codial_app.Classes.Talaba
import developer.abdulaziz.my_codial_app.databinding.ItemShowTalabaBinding

class MyTalabaAdapter(
    private var list: ArrayList<Talaba>,
    private var onItemClickListener: OnMenuClickListener
) :
    RecyclerView.Adapter<MyTalabaAdapter.ViewHolderTalaba>() {

    inner class ViewHolderTalaba(private val binding: ItemShowTalabaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(talaba: Talaba, position: Int) {
            binding.apply {
                talabaName.text = "${talaba.name} ${talaba.surname}"
                editTalaba.setOnClickListener { onItemClickListener.onEdit(talaba, position) }
                deleteTalaba.setOnClickListener { onItemClickListener.onDelete(talaba, position) }
                date.text = talaba.regDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTalaba {
        val bin = ItemShowTalabaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTalaba(bin)
    }

    override fun onBindViewHolder(holder: ViewHolderTalaba, position: Int) =
        holder.onBind(list[position], position)

    override fun getItemCount(): Int = list.size

    interface OnMenuClickListener {
        fun onEdit(talaba: Talaba, position: Int)
        fun onDelete(talaba: Talaba, position: Int)
    }
}

