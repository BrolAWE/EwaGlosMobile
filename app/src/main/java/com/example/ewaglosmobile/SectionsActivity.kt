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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class SectionsActivity : Activity() {

    lateinit var vRecView: RecyclerView
    //lateinit var vRecView: MaterialCardView
    var request: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sections)
        vRecView = findViewById<RecyclerView>(R.id.act2_recView)
        val lan = intent.getStringExtra("lan")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/section?format=json")
                .map { Gson().fromJson(it, SectionAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({

            val section =
                Section(
                    it.sections.mapTo(
                        RealmList<SectionItem>(),
                        { section ->
                            SectionItem(
                                section.code,
                                section.color,
                                section.translations.mapTo(RealmList<SectionTranslationItem>(),
                                    { translation ->
                                        SectionTranslationItem(
                                            translation.language,
                                            translation.name
                                        )
                                    })
                            )
                        })
                )

            Realm.getDefaultInstance().executeTransaction { realm ->

                val oldList=realm.where(Section::class.java).findAll()
                if(oldList.size>0)
                    for(item in oldList)
                        item.deleteFromRealm()

                realm.copyToRealm(section)


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
            val section=realm.where(Section::class.java).findAll()
            if(section.size>0){
                vRecView.adapter = SectionsRecAdapter(this,section[0]!!.sections,lan)
                vRecView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
            }
        }
    }

}