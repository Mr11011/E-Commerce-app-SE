//package com.example.e_commerceappv1
//
//import android.content.Intent
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    private val image: ImageView
//    private val names: TextView
//
//    init {
//        image = itemView.findViewById(R.id.Image_layout)
//        names = itemView.findViewById(R.id.item_label)
//    }
//
//    fun bind(dataModel: DataModel) {
//        names.text = dataModel.ProductName
//        image.setImageResource(dataModel.Image)
//
//    }
//    private fun navigate() {
//        itemView.setOnClickListener {
//
//            val intent: Intent = Intent(itemView.context, MainActivity2::class.java)
//            val currentF = ProductList[layoutPosition]
//            intent.putExtra("Product", currentF)
//            intent.putExtra("Product", currentF.Description)
//            intent.putExtra("Product", currentF.Image)
//            itemView.context.startActivity(intent)
//
//
//        }
//    }
//}
