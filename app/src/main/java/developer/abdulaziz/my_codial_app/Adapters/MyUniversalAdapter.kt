package developer.abdulaziz.my_codial_app.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.databinding.ItemUniversalBinding

class MyUniversalAdapter(
    private var list: ArrayList<Kurslar>,
    private var onItemClickListener: OnMenuClickListener
) :
    RecyclerView.Adapter<MyUniversalAdapter.UniversalVH>() {

    inner class UniversalVH(private val binding: ItemUniversalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(kurslar: Kurslar, position: Int) {
            binding.apply {
                itemName.text = kurslar.name
                item.setOnClickListener {
                    onItemClickListener.onClick(kurslar, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversalVH {
        val bin = ItemUniversalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversalVH(bin)
    }

    override fun onBindViewHolder(holder: UniversalVH, position: Int) =
        holder.onBind(list[position], position)

    override fun getItemCount(): Int = list.size

    interface OnMenuClickListener {
        fun onClick(kurslar: Kurslar, position: Int)
    }
}

