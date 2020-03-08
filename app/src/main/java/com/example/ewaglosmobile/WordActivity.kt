package com.example.ewaglosmobile

import android.app.Activity
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WordActivity : Activity() {

    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val str = intent.getStringExtra("tag1")
        val lan=intent.getStringExtra("lan")
        val vWord=findViewById<TextView>(R.id.textView_RU)
        val vTranslation=findViewById<TextView>(R.id.textView_EN)
        val vWordDef=findViewById<TextView>(R.id.textView_RU_def)
        val vTranslationDef=findViewById<TextView>(R.id.textView_EN_def)
        val vWordKom=findViewById<TextView>(R.id.textView_RU_kom)
        val vTranslationKom=findViewById<TextView>(R.id.textView_EN_kom)
        val vImage=findViewById<ImageView>(R.id.imageView)
        val vCheckEN = findViewById<RadioButton>(R.id.radioButton_EN)
        val vCheckDE = findViewById<RadioButton>(R.id.radioButton_DE)
        val vCheckBG = findViewById<RadioButton>(R.id.radioButton_BG)

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/word/"+str+"?format=json")
                .map { Gson().fromJson(it, WordsItemAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            val wor=it
            for (item in wor.translations){
                if(item.language=="RU"){
                    vWord.setText(item.name)
                    vWordDef.setText("Описание:"+item.definition)
                    vWordKom.setText("Комментарий:"+item.comment)
                }
            }
            for (item in wor.translations){
                if(item.language=="EN"){
                    vTranslation.setText(item.name)
                    vTranslationDef.setText("Definition:"+item.definition)
                    vTranslationKom.setText("Comment:"+item.comment)
                }
            }
            vCheckEN.setOnClickListener{
                for (item in wor.translations){
                    if(item.language=="EN"){
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definition:"+item.definition)
                        vTranslationKom.setText("Comment:"+item.comment)
                    }
                }
            }
            vCheckDE.setOnClickListener{
                for (item in wor.translations){
                    if(item.language=="DE"){
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definition:"+item.definition)
                        vTranslationKom.setText("Comment:"+item.comment)
                    }
                }
            }
            vCheckBG.setOnClickListener{
                for (item in wor.translations){
                    if(item.language=="BG"){
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definition:"+item.definition)
                        vTranslationKom.setText("Comment:"+item.comment)
                    }
                }
            }
            Picasso.with(vImage.context).load("https://res.cloudinary.com/hucj3dnre/"+it.image).into(vImage)

        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

}