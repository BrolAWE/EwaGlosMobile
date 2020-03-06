package com.example.ewaglosmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SectionsRecAdapter(
    var mContext: Context,
    val sections: ArrayList<SectionItemAPI>,
    lan: String
) :
    RecyclerView.Adapter<SectionsRecHolder>() {
    val lan = lan
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

class SubsectionsRecAdapter(
    var mContext: Context,
    val subsections: ArrayList<SubsectionItemAPI>,
    lan: String
) :
    RecyclerView.Adapter<SubsectionRecHolder>() {
    val lan = lan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubsectionRecHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return SubsectionRecHolder(view, lan)
    }

    override fun getItemCount(): Int {
        return subsections.size
    }

    override fun onBindViewHolder(holder: SubsectionRecHolder, position: Int) {
        val subsection = subsections[position]
        holder.bind(mContext, subsection)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}

class WordsRecAdapter(var mContext: Context, val words: ArrayList<WordItemAPI>, lan: String) :
    RecyclerView.Adapter<WordsRecHolder>() {
    val lan = lan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsRecHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return WordsRecHolder(view, lan)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: WordsRecHolder, position: Int) {
        val word = words[position]
        holder.bind(mContext, word)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}