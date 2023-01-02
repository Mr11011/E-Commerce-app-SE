package com.example.buywzme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.buyweme.databinding.SizeRvItemBinding

class SizeAdapter : RecyclerView.Adapter<SizeAdapter.SizesViewHolder>() {

    private var selectedPosition = -1

    inner class SizesViewHolder(private val binding: SizeRvItemBinding) :
        ViewHolder(binding.root) {

        fun bind(size: String, position: Int) {


           binding.tvSize.text =size

            if (position == selectedPosition) {
                binding.apply {
                    ImageShadow.visibility == View.VISIBLE


                }

            } else {
                binding.apply {
                    ImageShadow.visibility == View.INVISIBLE

                }

            }


        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
       return SizesViewHolder(
           SizeRvItemBinding.inflate(LayoutInflater.from(parent.context))

       )

    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        val size = differ.currentList[position]
        holder.bind(size, position)
        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)

            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(size)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onItemClick: ((String) -> Unit)? = null

}