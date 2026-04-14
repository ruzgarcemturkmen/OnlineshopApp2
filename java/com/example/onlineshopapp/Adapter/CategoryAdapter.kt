import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.Activity.ListItemsActivity
import com.example.onlineshopapp.Domain.CategoryModel
import com.example.onlineshopapp.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title

        if (selectedPosition == position) {
            holder.binding.titleTxt.setBackgroundResource(com.example.onlineshopapp.R.drawable.purple_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(holder.itemView.context, com.example.onlineshopapp.R.color.white)
            )
        } else {
            holder.binding.titleTxt.setBackgroundResource(com.example.onlineshopapp.R.drawable.white_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(holder.itemView.context, com.example.onlineshopapp.R.color.black)
            )
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            val intent = Intent(holder.itemView.context, ListItemsActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("id", item.id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
