package com.example.ewaglosmobile

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class AboutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val vTextViewHead = findViewById<TextView>(R.id.textView_head)
        val vTextViewBody = findViewById<TextView>(R.id.textView_body)
        val vTextViewFoot = findViewById<TextView>(R.id.textView_foot)
        val lan = intent.getStringExtra("lan")
        if (lan == "RU") {
            vTextViewHead.setText("О проекте")
            vTextViewBody.setText(
                "Как любая другая область профессиональной" +
                        "                деятельности, реставрация обладает специальным языком" +
                        "                – терминологией, или специальной лексикой, которая," +
                        "                вместе с самой реставрацией, прошла сложный и" +
                        "                длительный путь развития и на сегодняшний день" +
                        "                представляет собой стройную исторически сложившуюся" +
                        "                систему."
            )
            vTextViewFoot.setText("Юлия Грибер" +
                    "                Доктор культурологии," +
                    "                профессор кафедры философии и социологии" +
                    "                Смоленского государственного университета," +
                    "                Смоленск, декабрь 2018")

        }
    }
}