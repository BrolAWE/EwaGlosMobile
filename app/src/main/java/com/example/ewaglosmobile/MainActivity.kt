package com.example.ewaglosmobile

import android.lectures.createRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var vText: TextView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vButton=findViewById<Button>(R.id.button)
        vButton.setOnClickListener{
 //           val i= Intent(this,SecondActivity::class.java)
//            startActivity(i)

            val o =
                createRequest("https://ewaglos.herokuapp.com/api/section?format=json")
                    .map { Gson().fromJson(it, SectionAPI::class.java) }
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            request = o.subscribe({
                for (section in it.sections)
                    Log.w("tag", "color ${section.color} code ${section.code} translations ${section.translations[0].name}")
            }, {
                Log.e("tag", "", it)
            })
        }


    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }
}

class SectionAPI(
    val sections: ArrayList<SectionItemAPI>
)

class TranslationItemAPI(
    val language: String,
    val name: String
)

class SectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<TranslationItemAPI>
)
