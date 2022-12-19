package com.example.e_commerceappv1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val data2: DataModel? = intent.getParcelableExtra("Product")
        val text: TextView = findViewById(R.id.label2)

        text.text = "${data2?.ProductName}"
        val image2: ImageView = findViewById(R.id.image2)
        if (data2 != null) {
            image2.setImageResource(data2.Image)
        }
        val desc: TextView = findViewById(R.id.label3)
        desc.text = "${data2?.Description}"
    }
}