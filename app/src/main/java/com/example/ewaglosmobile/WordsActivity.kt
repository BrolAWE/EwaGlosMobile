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
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class WordsActivity : Activity() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        vRecView = findViewById<RecyclerView>(R.id.act4_recView)
        val str = intent.getStringExtra("tag1")
        val lan = intent.getStringExtra("lan")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/words/" + str + "?format=json")
                .map { Gson().fromJson(it, WordAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({

            val word =
                Word(
                    it.words.mapTo(
                        RealmList<WordItem>(),
                        { word ->
                            WordItem(
                                word.code,
                                word.image,
                                word.translations.mapTo(
                                    RealmList<WordTranslationItem>(),
                                    { translation ->
                                        WordTranslationItem(
                                            translation.language,
                                            translation.name,
                                            translation.definition,
                                            translation.comment,
                                            translation.image_description,
                                            translation.synonyms.mapTo(
                                                RealmList<SynonymItem>(),
                                                { synonym ->
                                                    SynonymItem(synonym.synonym)
                                                }
                                            )
                                        )
                                    }),
                                word.close_senses.mapTo(
                                    RealmList<CloseSenseItem>(),
                                    { closesense ->
                                        CloseSenseItem(
                                            closesense.close_sense
                                        )
                                    }
                                )
                            )
                        })
                )

            Realm.getDefaultInstance().executeTransaction { realm ->

                val oldList = realm.where(Word::class.java).findAll()
                if (oldList.size > 0)
                    for (item in oldList)
                        if(item.words.size==0){
                            item.deleteFromRealm()
                        }
                        else{
                            if (item.words[0]!!.code.substring(0, 6) == str)
                                item.deleteFromRealm()
                        }

                realm.copyToRealm(word)
            }

            showRecView(lan, str)

        }, {
            Log.e("tag", "", it)
            showRecView(lan, str)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun showRecView(lan: String, str: String) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            val word = realm.where(Word::class.java).findAll()
            if (word.size > 0) {
                for (item in word) {
                    if(item.words.size>0)
                        if (item.words[0]!!.code.substring(0, 6) == str) {
                            vRecView.adapter = WordsRecAdapter(this, item!!.words, lan)
                            vRecView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
                        }
                }
            }
        }
    }

}