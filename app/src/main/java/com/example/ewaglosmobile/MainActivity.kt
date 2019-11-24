package com.example.ewaglosmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        verticalLayout {

            button {
                text = "МРПО1"

            }.onClick {
                mrpo1()
            }

            textView = textView {
                text = ""
            }
        }
    }

    private fun mrpo1() {
        Thread {
            val (request, response, result) = Fuel.get("https://ewaglos.herokuapp.com/api/section?format=json")
                .responseString()
            runOnUiThread {
                result.fold({ data ->
                    textView.text = "${data}"
                }, { error ->
                    textView.text = error.localizedMessage
                })
            }
        }.start()

    }
}
