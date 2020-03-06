package com.example.ewaglosmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SectionsRecAdapter(var mContext: Context, val sections: ArrayList<SectionItemAPI>, lan:String) :
    RecyclerView.Adapter<SectionsRecHolder>() {
    val lan=lan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsRecHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return SectionsRecHolder(view, lan)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun onBindViewHolder(holder: SectionsRecHolder, position: Int) {
        val section = sections[position]
        holder?.bind(mContext, section)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}