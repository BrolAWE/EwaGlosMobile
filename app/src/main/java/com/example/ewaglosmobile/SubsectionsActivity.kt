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

class ThirdActivity : Activity() {
    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subsections)

        vRecView = findViewById<RecyclerView>(R.id.act3_recView)
        val str = intent.getStringExtra("tag1")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/subsection/" + str + "?format=json")
                .map { Gson().fromJson(it, SubsectionAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            showRecView(it.subsections)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun showRecView(sectionList: ArrayList<SubsectionItemAPI>) {
        vRecView.adapter = SubsectionsRecAdapter(this, sectionList)
        vRecView.layoutManager = LinearLayoutManager(this)
    }

}

private class SubsectionsRecAdapter(var mContext: Context, val subsections: ArrayList<SubsectionItemAPI>) :
    RecyclerView.Adapter<RecHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return RecHolder(view)
    }

    override fun getItemCount(): Int {
        return subsections.size
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val subsection = subsections[position]
        holder.bind(mContext, subsection)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}

class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(mContext: Context, subsection: SubsectionItemAPI) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        val vThumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        vTitle.text = subsection.translations[0].name
        vTitle.setTextColor(Color.parseColor(subsection.color))
        vThumb.setBackgroundColor(Color.parseColor(subsection.color))
        itemView.setOnClickListener {
            val i = Intent(mContext, FourthActivity::class.java)
            i.putExtra("tag1", subsection.code)
            mContext.startActivity(i)
        }
    }
}