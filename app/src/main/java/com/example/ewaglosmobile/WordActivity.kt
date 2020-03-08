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
        val lan = intent.getStringExtra("lan")
        val vWord = findViewById<TextView>(R.id.textView_RU)
        val vTranslation = findViewById<TextView>(R.id.textView_EN)
        val vWordDef = findViewById<TextView>(R.id.textView_RU_def)
        val vTranslationDef = findViewById<TextView>(R.id.textView_EN_def)
        val vWordKom = findViewById<TextView>(R.id.textView_RU_kom)
        val vTranslationKom = findViewById<TextView>(R.id.textView_EN_kom)
        val vImage = findViewById<ImageView>(R.id.imageView)
        val vCheckEN = findViewById<RadioButton>(R.id.radioButton_EN)
        val vCheckDE = findViewById<RadioButton>(R.id.radioButton_DE)
        val vCheckBG = findViewById<RadioButton>(R.id.radioButton_BG)
        val vCheckES = findViewById<RadioButton>(R.id.radioButton_ES)
        val vCheckRO = findViewById<RadioButton>(R.id.radioButton_RO)
        val vCheckPL = findViewById<RadioButton>(R.id.radioButton_PL)
        val vCheckIT = findViewById<RadioButton>(R.id.radioButton_IT)
        val vCheckHU = findViewById<RadioButton>(R.id.radioButton_HU)
        val vCheckHR = findViewById<RadioButton>(R.id.radioButton_HR)
        val vCheckFR = findViewById<RadioButton>(R.id.radioButton_FR)

        val o =
            createRequest("https://ewaglos.herokuapp.com/api/word/" + str + "?format=json")
                .map { Gson().fromJson(it, WordItemAPI::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            val wor = it
            for (item in wor.translations) {
                if (item.language == "RU") {
                    vWord.setText(item.name)
                    vWordDef.setText("Определение:" + item.definition)
                    vWordKom.setText("Комментарий:" + item.comment)
                }
            }
            for (item in wor.translations) {
                if (item.language == "EN") {
                    vTranslation.setText(item.name)
                    vTranslationDef.setText("Definition:" + item.definition)
                    vTranslationKom.setText("Comment:" + item.comment)
                }
            }
            vCheckEN.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "EN") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definition:" + item.definition)
                        vTranslationKom.setText("Comment:" + item.comment)
                    }
                }
            }
            vCheckDE.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "DE") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definition:" + item.definition)
                        vTranslationKom.setText("Kommentar:" + item.comment)
                    }
                }
            }
            vCheckBG.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "BG") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Дефиниция:" + item.definition)
                        vTranslationKom.setText("Коментар:" + item.comment)
                    }
                }
            }
            vCheckES.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "ES") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definición:" + item.definition)
                        vTranslationKom.setText("Comentario:" + item.comment)
                    }
                }
            }
            vCheckRO.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "RO") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definiţie:" + item.definition)
                        vTranslationKom.setText("Adnotare:" + item.comment)
                    }
                }
            }
            vCheckPL.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "PL") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definicja:" + item.definition)
                        vTranslationKom.setText("Komentarz:" + item.comment)
                    }
                }
            }
            vCheckIT.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "IT") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definizione:" + item.definition)
                        vTranslationKom.setText("Commento:" + item.comment)
                    }
                }
            }
            vCheckHU.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "HU") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Meghatározás:" + item.definition)
                        vTranslationKom.setText("Megjegyzés:" + item.comment)
                    }
                }
            }
            vCheckHR.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "HR") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Definicija:" + item.definition)
                        vTranslationKom.setText("Napomena:" + item.comment)
                    }
                }
            }
            vCheckFR.setOnClickListener {
                for (item in wor.translations) {
                    if (item.language == "FR") {
                        vTranslation.setText(item.name)
                        vTranslationDef.setText("Définition:" + item.definition)
                        vTranslationKom.setText("Commentaires:" + item.comment)
                    }
                }
            }
            Picasso.with(vImage.context).load("https://res.cloudinary.com/hucj3dnre/" + it.image)
                .into(vImage)

        }, {
            Log.e("tag", "", it)
        })
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

}