package com.example.ewaglosmobile

import io.realm.RealmList
import io.realm.RealmObject

class SectionAPI(
    val sections: ArrayList<SectionItemAPI>
)

class SectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SectionTranslationItemAPI>
)

open class SectionTranslationItem(
    var language: String = "",
    var name: String = ""
) : RealmObject()

open class Section(
    var sections: RealmList<SectionItem> = RealmList<SectionItem>()
) : RealmObject()

open class SectionItem(
    var code: String = "",
    var color: String = "",
    var translations: RealmList<SectionTranslationItem> = RealmList<SectionTranslationItem>()
) : RealmObject()

class SectionTranslationItemAPI(
    val language: String,
    val name: String
)


class SubsectionAPI(
    val subsections: ArrayList<SubsectionItemAPI>
)

class SubsectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SubsectionTranslationItemAPI>
)

class SubsectionTranslationItemAPI(
    val language: String,
    val name: String
)

class WordAPI(
    val words: ArrayList<WordItemAPI>
)

class WordItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationItemAPI>,
    val close_senses: ArrayList<CloseSenseItemAPI>
)

class WordTranslationItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymItemAPI>
)

class SynonymItemAPI(
    val synonym: String
)

class CloseSenseItemAPI(
    val close_sense: String
)




