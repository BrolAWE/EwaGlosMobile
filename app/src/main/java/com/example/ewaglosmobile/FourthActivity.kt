package com.example.ewaglosmobile

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FourthActivity : Activity() {

    lateinit var vText: TextView
    lateinit var vList: LinearLayout
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        vList = findViewById<LinearLayout>(R.id.sec_list)
        val str = intent.getStringExtra("tag1")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/words/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            ShowLinearLayout(it.words)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun ShowLinearLayout(sectionList: ArrayList<WordItemAPI>) {
        val inflater = layoutInflater
        for (f in sectionList) {
            val view = inflater.inflate(R.layout.list_item, vList, false)
            val vTitle = view.findViewById<TextView>(R.id.item_title)
            vTitle.text = f.translations[0].name
            vTitle.setOnClickListener {
                val i = Intent(this, FifthActivity::class.java)
                i.putExtra("tag1", f.code)
                startActivityForResult(i, 0)
            }
            vList.addView(view)
        }
    }

}

class WordsAPI(
    val words: ArrayList<WordItemAPI>
)


class SynonymItemAPI(
    val synonym: String
)

class CloseSenseItemAPI(
    val close_sense: String
)


class WordTranslationItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymItemAPI>
)

class WordItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationItemAPI>,
    val close_senses: ArrayList<CloseSenseItemAPI>
)