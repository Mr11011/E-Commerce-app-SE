package com.example.buywzme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.buyweme.databinding.ViewpagerImageItemBinding

class viewPager2Images : RecyclerView.Adapter<viewPager2Images.viewPagerHolder>() {

    inner class viewPagerHolder(val binding: ViewpagerImageItemBinding) : ViewHolder(binding.root){
        fun bind(imagePath:String){
            Glide.with(itemView).load(imagePath).into(binding.ImageProductDetails)

        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer (this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerHolder {
        return viewPagerHolder(
            ViewpagerImageItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: viewPagerHolder, position: Int) {
        val image= differ.currentList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }




}












