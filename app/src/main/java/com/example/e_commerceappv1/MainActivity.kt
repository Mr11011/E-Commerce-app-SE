package com.example.e_commerceappv1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        val listOfProduct = getListOfProducts()
        val productadapter=Adapter(listOfProduct)
        recyclerView.adapter=productadapter

    }

    private fun getListOfProducts(): MutableList<DataModel> {
        val products = mutableListOf<DataModel>()
        products.add(DataModel("Dove", "Dove", R.drawable.dove))
        products.add(DataModel("Hp", "HP Laptop", R.drawable.hp))
        products.add(DataModel("JBL", "JBL", R.drawable.jbl))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.l_oreal))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.msurface))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.pantene))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.pixel4a))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.samsung))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.sonyhead))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.tresemme))
        products.add(DataModel("IPhone", "IPhone 14", R.drawable.xiaomi2))
return products
    }
}
