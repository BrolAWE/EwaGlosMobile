package com.example.ewaglosmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        var lan="EN"
        val vButton = findViewById<Button>(R.id.button_open)
        val vCheckRu = findViewById<RadioButton>(R.id.radioButton_ru)
        val vCheckEn = findViewById<RadioButton>(R.id.radioButton_en)
        vButton.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            i.putExtra("lan",lan)
            startActivity(i)
        }
        vCheckRu.setOnClickListener {
            vButton.setText("Открыть словарь")
            lan="RU"
        }
        vCheckEn.setOnClickListener {
            vButton.setText("Open book")
            lan="EN"
        }
    }
}
