package com.example.ewaglosmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vButton=findViewById<Button>(R.id.button)
        vButton.setOnClickListener{
            val i= Intent(this,SecondActivity::class.java)
            startActivity(i)
        }
    }
}
