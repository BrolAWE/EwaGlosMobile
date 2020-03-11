package com.example.ewaglosmobile

import android.app.Activity
import android.lectures.createRequest
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class WordActivity : Activity() {

    var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val str = intent.getStringExtra("tag1")
        Draw(str)
    }

    override fun onDestroy() {
        request?.dispose()
        super.onDestroy()
    }

    fun Draw(str: String) {

        Realm.getDefaultInstance().executeTransaction { realm ->
            val word = realm.where(Word::class.java).findAll()
            if (word.size > 0) {
                for (ite in word)
                    for (item in ite!!.words)
                        if (item.code == str) {
                            val worr = item
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


                            if (lan == "EN") {
                                vCheckEN.setText("RU")
                                for (item in worr.translations) {
                                    if (item.language == "EN") {
                                        vWord.setText(item.name)
                                        vWordDef.setText("Definition:" + item.definition)
                                        vWordKom.setText("Comment:" + item.comment)
                                    }
                                }
                                for (item in worr.translations) {
                                    if (item.language == "RU") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Определение:" + item.definition)
                                        vTranslationKom.setText("Комментарий:" + item.comment)
                                    }
                                }
                            } else {
                                if (lan == "RU") {
                                    for (item in worr.translations) {
                                        if (item.language == "RU") {
                                            vWord.setText(item.name)
                                            vWordDef.setText("Определение:" + item.definition)
                                            vWordKom.setText("Комментарий:" + item.comment)
                                        }
                                    }
                                    for (item in worr.translations) {
                                        if (item.language == "EN") {
                                            vTranslation.setText(item.name)
                                            vTranslationDef.setText("Definition:" + item.definition)
                                            vTranslationKom.setText("Comment:" + item.comment)
                                        }
                                    }
                                }
                            }
                            vCheckEN.setOnClickListener {
                                if (lan == "EN") {
                                    for (item in worr.translations) {
                                        if (item.language == "RU") {
                                            vTranslation.setText(item.name)
                                            vTranslationDef.setText("Определение:" + item.definition)
                                            vTranslationKom.setText("Комментарий:" + item.comment)
                                        }
                                    }
                                } else {
                                    if (lan == "RU") {
                                        for (item in worr.translations) {
                                            if (item.language == "EN") {
                                                vTranslation.setText(item.name)
                                                vTranslationDef.setText("Definition:" + item.definition)
                                                vTranslationKom.setText("Comment:" + item.comment)
                                            }
                                        }
                                    }
                                }
                            }
                            vCheckDE.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "DE") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definition:" + item.definition)
                                        vTranslationKom.setText("Kommentar:" + item.comment)
                                    }
                                }
                            }
                            vCheckBG.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "BG") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Дефиниция:" + item.definition)
                                        vTranslationKom.setText("Коментар:" + item.comment)
                                    }
                                }
                            }
                            vCheckES.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "ES") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definición:" + item.definition)
                                        vTranslationKom.setText("Comentario:" + item.comment)
                                    }
                                }
                            }
                            vCheckRO.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "RO") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definiţie:" + item.definition)
                                        vTranslationKom.setText("Adnotare:" + item.comment)
                                    }
                                }
                            }
                            vCheckPL.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "PL") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definicja:" + item.definition)
                                        vTranslationKom.setText("Komentarz:" + item.comment)
                                    }
                                }
                            }
                            vCheckIT.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "IT") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definizione:" + item.definition)
                                        vTranslationKom.setText("Commento:" + item.comment)
                                    }
                                }
                            }
                            vCheckHU.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "HU") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Meghatározás:" + item.definition)
                                        vTranslationKom.setText("Megjegyzés:" + item.comment)
                                    }
                                }
                            }
                            vCheckHR.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "HR") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Definicija:" + item.definition)
                                        vTranslationKom.setText("Napomena:" + item.comment)
                                    }
                                }
                            }
                            vCheckFR.setOnClickListener {
                                for (item in worr.translations) {
                                    if (item.language == "FR") {
                                        vTranslation.setText(item.name)
                                        vTranslationDef.setText("Définition:" + item.definition)
                                        vTranslationKom.setText("Commentaires:" + item.comment)
                                    }
                                }
                            }
                            Picasso.with(vImage.context)
                                .load("https://res.cloudinary.com/hucj3dnre/" + worr.image)
                                .into(vImage)
                        }
            }
        }

    }

}