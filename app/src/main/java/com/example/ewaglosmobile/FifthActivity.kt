package com.example.ewaglosmobile

import android.app.Activity
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FifthActivity : Activity() {

    lateinit var vText: TextView
    lateinit var Word: TextView
    lateinit var Translation: TextView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth_activity)

        val str = intent.getStringExtra("tag1")
        val vWord=findViewById<TextView>(R.id.textView3)
        val vTranslation=findViewById<TextView>(R.id.textView4)

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/word/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsItemAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            vWord.setText(it.translations[0].name)
            vTranslation.setText(it.translations[1].name)

        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

}

class WordAPI(
    val word: ArrayList<WordsItemAPI>
)

class SynonymsItemAPI(
    val synonym: String
)

class CloseSensesItemAPI(
    val close_sense: String
)


class WordTranslationsItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymsItemAPI>
)

class WordsItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationsItemAPI>,
    val close_senses: ArrayList<CloseSensesItemAPI>
)