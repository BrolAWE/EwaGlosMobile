package com.example.ewaglosmobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WordsActivity : Activity() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)


        vRecView = findViewById<RecyclerView>(R.id.act4_recView)
        val str = intent.getStringExtra("tag1")
        val lan=intent.getStringExtra("lan")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/words/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            showRecView(it.words, lan)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun showRecView(wordList: ArrayList<WordItemAPI>, lan:String){
        vRecView.adapter= WordsRecAdapter(this,wordList, lan)
        vRecView.layoutManager= LinearLayoutManager(this)
    }

}