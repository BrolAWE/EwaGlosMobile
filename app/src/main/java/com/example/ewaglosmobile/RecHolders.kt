package com.example.ewaglosmobile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SectionsRecHolder(view: View,lan:String) : RecyclerView.ViewHolder(view) {
    val lan=lan
    fun bind(mContext: Context, section: SectionItemAPI) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        val vThumb = itemView.findViewById<ImageView>(R.id.item_thumb)
        for (item in section.translations){
            if(item.language==lan){
                vTitle.text = item.name
            }
        }
        vTitle.setTextColor(Color.parseColor(section.color))
        vThumb.setBackgroundColor(Color.parseColor(section.color))
        itemView.setOnClickListener {
            val i = Intent(mContext, ThirdActivity::class.java)
            i.putExtra("tag1", section.code)
            mContext.startActivity(i)
        }
    }
}