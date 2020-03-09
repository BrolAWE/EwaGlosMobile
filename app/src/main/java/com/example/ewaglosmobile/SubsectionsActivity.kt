package com.example.ewaglosmobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class SubsectionsActivity : Activity() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subsections)

        vRecView = findViewById<RecyclerView>(R.id.act3_recView)
        val str = intent.getStringExtra("tag1")
        val lan = intent.getStringExtra("lan")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/subsection/" + str + "?format=json")
                .map { Gson().fromJson(it, SubsectionAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({

            val subsection =
                Subsection(
                    it.subsections.mapTo(
                        RealmList<SubsectionItem>(),
                        { subsection ->
                            SubsectionItem(
                                subsection.code,
                                subsection.color,
                                subsection.translations.mapTo(
                                    RealmList<SubsectionTranslationItem>(),
                                    { translation ->
                                        SubsectionTranslationItem(
                                            translation.language,
                                            translation.name
                                        )
                                    })
                            )
                        })
                )

            Realm.getDefaultInstance().executeTransaction { realm ->

                val oldList=realm.where(Subsection::class.java).findAll()
                if(oldList.size>0)
                    for(item in oldList)
                        item.deleteFromRealm()

                realm.copyToRealm(subsection)
            }

            showRecView(lan)

        }, {
            Log.e("tag", "", it)
            showRecView(lan)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun showRecView(lan: String) {
        Realm.getDefaultInstance().executeTransaction{realm->
            val subsection=realm.where(Subsection::class.java).findAll()
            if(subsection.size>0){
                vRecView.adapter = SubsectionsRecAdapter(this,subsection[0]!!.subsections,lan)
                vRecView.layoutManager = LinearLayoutManager(this)
            }
        }
    }

}