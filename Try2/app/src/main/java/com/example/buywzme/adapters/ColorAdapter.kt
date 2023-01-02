package com.example.buywzme.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.buyweme.databinding.ColorRvItemBinding

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ColorsViewHolder>() {

    private var selectedPosition = -1

    inner class ColorsViewHolder(private val binding: ColorRvItemBinding) :
        ViewHolder(binding.root) {

        fun bind(color: Int, position: Int) {

            val imageDrawable = ColorDrawable(color)
            binding.ImageColor.setImageDrawable(imageDrawable)

            if (position == selectedPosition) {
                binding.apply {
                    ImageShadow.visibility == View.VISIBLE
                    ImagePicker.visibility = View.VISIBLE

                }

            } else {
                binding.apply {
                    ImageShadow.visibility == View.INVISIBLE
                    ImagePicker.visibility = View.INVISIBLE
                }

            }


        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        return ColorsViewHolder(ColorRvItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val color = differ.currentList[position]
        holder.bind(color, position)
        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)

            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(color)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onItemClick: ((Int) -> Unit)? = null

}