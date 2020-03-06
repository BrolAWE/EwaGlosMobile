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

class FourthActivity : Activity() {

    lateinit var vRecView: RecyclerView
    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)


        vRecView = findViewById<RecyclerView>(R.id.act4_recView)
        val str = intent.getStringExtra("tag1")

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/words/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            showRecccView(it.words)
        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun showRecccView(wordList: ArrayList<WordItemAPI>){
        vRecView.adapter= RecccAdapter(this,wordList)
        vRecView.layoutManager= LinearLayoutManager(this)
    }

}

private class RecccAdapter(var mContext: Context, val words: ArrayList<WordItemAPI>) : RecyclerView.Adapter<RecccHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecccHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return RecccHolder(view)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: RecccHolder, position: Int) {
        val word=words[position]
        holder.bind(mContext,word)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}

class RecccHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(mContext: Context,word:WordItemAPI){
        val vTitle=itemView.findViewById<TextView>(R.id.item_title)
        val vThumb=itemView.findViewById<ImageView>(R.id.item_thumb)
        vTitle.text=word.translations[0].name
        Picasso.with(vThumb.context).load("https://res.cloudinary.com/hucj3dnre/"+word.image).into(vThumb)
        itemView.setOnClickListener {
            val i = Intent(mContext, FifthActivity::class.java)
            i.putExtra("tag1", word.code)
            mContext.startActivity(i)
        }
    }
}