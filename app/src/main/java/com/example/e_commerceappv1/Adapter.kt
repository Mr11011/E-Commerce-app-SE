package com.example.e_commerceappv1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


abstract class Adapter(private val ProductList: List<DataModel>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemProduct =
            LayoutInflater.from(parent.context).inflate(R.layout.itemlayout, parent, false)
        return ViewHolder(itemProduct)
    }

    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = ProductList[position]
        holder.bind(current)
    }


    override fun getItemCount(): Int {
        return ProductList.size
    }

    /////////////////////////////////////////////////////////////////
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView
        private val names: TextView

        init {
            image = itemView.findViewById(R.id.Image_layout)
            names = itemView.findViewById(R.id.item_label)
            navigate()
        }

        fun bind(dataModel: DataModel) {
            names.text = dataModel.ProductName
            image.setImageResource(dataModel.Image)

        }

        private fun navigate() {
            itemView.setOnClickListener {

                val intent: Intent = Intent(itemView.context, MainActivity2::class.java)
                val currentF = ProductList[layoutPosition]
                intent.putExtra("Product", currentF)
                intent.putExtra("Product", currentF.Description)
                intent.putExtra("Product", currentF.Image)
                itemView.context.startActivity(intent)


            }


        }
    }

}
