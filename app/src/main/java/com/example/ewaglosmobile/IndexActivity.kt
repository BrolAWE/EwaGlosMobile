package com.example.ewaglosmobile

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class IndexActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        var lan = "EN"
        val vButtonOpen = findViewById<Button>(R.id.button_open)
        val vButtonAbout = findViewById<Button>(R.id.button_about)
        val vButtonSearch = findViewById<Button>(R.id.button_search)
        val vCheckRu = findViewById<RadioButton>(R.id.radioButton_ru)
        val vCheckEn = findViewById<RadioButton>(R.id.radioButton_en)
        val vEditText = findViewById<EditText>(R.id.editText_search)
        vButtonOpen.setOnClickListener {
            val i = Intent(this, SectionsActivity::class.java)
            i.putExtra("lan", lan)
            startActivity(i)
        }
        vCheckRu.setOnClickListener {
            vButtonOpen.setText("Открыть словарь")
            vButtonAbout.setText("О проекте")
            vButtonSearch.setText("Поиск")
            lan = "RU"
        }
        vCheckEn.setOnClickListener {
            vButtonOpen.setText("Open book")
            vButtonAbout.setText("About")
            vButtonSearch.setText("Search")
            lan = "EN"
        }
        vButtonSearch.setOnClickListener {
            val tag1 = vEditText.text
            val i = Intent(this, SearchActivity::class.java)
            i.putExtra("lan", lan)
            i.putExtra("tag1", tag1.toString())
            startActivity(i)
        }
        vButtonAbout.setOnClickListener {
            val i = Intent(this, AboutActivity::class.java)
            i.putExtra("lan", lan)
            startActivity(i)
        }
    }
}
