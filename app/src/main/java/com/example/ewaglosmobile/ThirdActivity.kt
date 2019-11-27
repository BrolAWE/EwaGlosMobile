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

class ThirdActivity : Activity() {

    lateinit var vText: TextView
    lateinit var vList: LinearLayout
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        vList = findViewById<LinearLayout>(R.id.sec_list)
        val str = intent.getStringExtra("tag1")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/subsection/"+str+"?format=json")
                .map { Gson().fromJson(it, SubsectionAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            ShowLinearLayout(it.subsections)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun ShowLinearLayout(sectionList: ArrayList<SubsectionItemAPI>) {
        val inflater = layoutInflater
        for (f in sectionList) {
            val view = inflater.inflate(R.layout.list_item, vList, false)
            val vTitle = view.findViewById<TextView>(R.id.item_title)
            vTitle.text = f.translations[0].name
            vTitle.setTextColor(Color.parseColor(f.color))
            vTitle.setOnClickListener {
                val i = Intent(this, FourthActivity::class.java)
                i.putExtra("tag1", f.code)
                startActivityForResult(i, 0)
            }
            vList.addView(view)
        }
    }

}

class SubsectionAPI(
    val subsections: ArrayList<SubsectionItemAPI>
)

class SubsectionTranslationItemAPI(
    val language: String,
    val name: String
)

class SubsectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SubsectionTranslationItemAPI>
)