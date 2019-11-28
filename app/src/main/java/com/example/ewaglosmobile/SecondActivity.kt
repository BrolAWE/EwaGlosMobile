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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SecondActivity : Activity() {

    lateinit var vRecView: RecyclerView
    lateinit var vText: TextView
    lateinit var vList: LinearLayout
    var request: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        vRecView = findViewById<RecyclerView>(R.id.act2_recView)

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/section?format=json")
                .map { Gson().fromJson(it, SectionAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            showRecView(it.sections)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun ShowLinearLayout(sectionList:ArrayList<SectionItemAPI>){
        val inflater=layoutInflater
        for(f in sectionList){
            val view=inflater.inflate(R.layout.list_item, vList, false)
            val vTitle=view.findViewById<TextView>(R.id.item_title)
            vTitle.text=f.translations[0].name
            vTitle.setTextColor(Color.parseColor(f.color))
            vTitle.setOnClickListener {
                val i = Intent(this, ThirdActivity::class.java)
                i.putExtra("tag1", f.code)
                startActivityForResult(i, 0)
            }
            vList.addView(view)
        }
    }

    fun showRecView(sectionList: ArrayList<SectionItemAPI>){
        vRecView.adapter= ReccAdapter(this,sectionList)
        vRecView.layoutManager= LinearLayoutManager(this)
    }

}

class SectionAPI(
    val sections: ArrayList<SectionItemAPI>
)

class SectionTranslationItemAPI(
    val language: String,
    val name: String
)

class SectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SectionTranslationItemAPI>
)

private class ReccAdapter(var mContext: Context, val sections: ArrayList<SectionItemAPI>) : RecyclerView.Adapter<ReccHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReccHolder {
        val inflater= LayoutInflater.from(parent!!.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ReccHolder(view)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun onBindViewHolder(holder: ReccHolder, position: Int) {
        val section=sections[position]
        holder?.bind(mContext,section)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}

class ReccHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(mContext: Context, section:SectionItemAPI){
        val vTitle=itemView.findViewById<TextView>(R.id.item_title)
        val vThumb=itemView.findViewById<ImageView>(R.id.item_thumb)
        vTitle.text=section.translations[0].name
        vTitle.setTextColor(Color.parseColor(section.color))
        vThumb.setBackgroundColor(Color.parseColor(section.color))
        itemView.setOnClickListener {
            val i = Intent(mContext, ThirdActivity::class.java)
            i.putExtra("tag1", section.code)
            mContext.startActivity(i)
        }
    }
}