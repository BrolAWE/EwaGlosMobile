package com.example.ewaglosmobile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class SectionsRecHolder(view: View,lan:String) : RecyclerView.ViewHolder(view) {
    val lan=lan
    fun bind(mContext: Context, section: SectionItem) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        val vThumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        //val vThumb = itemView.findViewById<MaterialCardView>(R.id.item_thumb)
        for (item in section.translations){
            if(item.language==lan){
                vTitle.text = item.name
            }
        }
        vTitle.setTextColor(Color.parseColor(section.color!!))
        vThumb.setBackgroundColor(Color.parseColor(section.color!!))
        itemView.setOnClickListener {
            val i = Intent(mContext, SubsectionsActivity::class.java)
            i.putExtra("tag1", section.code)
            i.putExtra("lan",lan)
            mContext.startActivity(i)
        }
    }
}

class SubsectionRecHolder(view: View, lan:String) : RecyclerView.ViewHolder(view) {
    val lan=lan
    fun bind(mContext: Context, subsection: SubsectionItem) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        val vThumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        for (item in subsection.translations){
            if(item.language==lan){
                vTitle.text = item.name
            }
        }
        vTitle.setTextColor(Color.parseColor(subsection.color!!))
        vThumb.setBackgroundColor(Color.parseColor(subsection.color!!))
        itemView.setOnClickListener {
            val i = Intent(mContext, WordsActivity::class.java)
            i.putExtra("tag1", subsection.code)
            i.putExtra("lan",lan)
            mContext.startActivity(i)
        }
    }
}

class WordsRecHolder(view: View, lan:String):RecyclerView.ViewHolder(view){
    val lan=lan
    fun bind(mContext: Context,word:WordItem){
        val vTitle=itemView.findViewById<TextView>(R.id.item_title)
        val vThumb=itemView.findViewById<ImageView>(R.id.item_thumb)
        for (item in word.translations){
            if(item.language==lan){
                vTitle.text = item.name
            }
        }
        Picasso.with(vThumb.context).load("https://res.cloudinary.com/hucj3dnre/"+word.image).into(vThumb)
        itemView.setOnClickListener {
            val i = Intent(mContext, WordActivity::class.java)
            i.putExtra("tag1", word.code)
            i.putExtra("lan",lan)
            mContext.startActivity(i)
        }
    }
}