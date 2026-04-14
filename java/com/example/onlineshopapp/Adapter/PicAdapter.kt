package com.example.onlineshopapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshopapp.databinding.ViewholderPicBinding

class PicAdapter(val items: List<String>,private val onImageSelected:(String)-> Unit):
RecyclerView.Adapter<PicAdapter.Viewholder>()
{
    private var SelectedPosition=-1
    private var LastSelecetedPosition=-1
    class Viewholder(val binding: ViewholderPicBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicAdapter.Viewholder {
        val binding = ViewholderPicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PicAdapter.Viewholder, position: Int) {
      val item=items[position]
        holder.binding.pic.loadImage(item)
        holder.binding.root.setOnClickListener {
            val position=position
            if (position!= RecyclerView.NO_POSITION)
                LastSelecetedPosition=SelectedPosition
            SelectedPosition=position
            notifyItemChanged(LastSelecetedPosition)
            notifyItemChanged(SelectedPosition)
                onImageSelected(item)
        }
    }

    override fun getItemCount(): Int = items.size
    fun ImageView.loadImage(url: String){
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}