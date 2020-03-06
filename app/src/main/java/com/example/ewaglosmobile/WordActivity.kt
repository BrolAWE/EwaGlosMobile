package com.example.ewaglosmobile

import android.app.Activity
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FifthActivity : Activity() {

    lateinit var vText: TextView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val str = intent.getStringExtra("tag1")
        val lan=intent.getStringExtra("lan")
        val vWord=findViewById<TextView>(R.id.textView3)
        val vTranslation=findViewById<TextView>(R.id.textView4)

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/word/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsItemAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            for (item in it.translations){
                if(item.language=="RU"){
                    vWord.setText(item.name)
                }
            }
            for (item in it.translations){
                if(item.language=="EN"){
                    vTranslation.setText(item.name)
                }
            }

        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

}