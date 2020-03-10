package com.example.ewaglosmobile

import io.realm.RealmList
import io.realm.RealmObject

class SectionAPI(
    val sections: ArrayList<SectionItemAPI>
)

open class Section(
    var sections: RealmList<SectionItem> = RealmList<SectionItem>()
) : RealmObject()

class SectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SectionTranslationItemAPI>
)

open class SectionItem(
    var code: String = "",
    var color: String = "",
    var translations: RealmList<SectionTranslationItem> = RealmList<SectionTranslationItem>()
) : RealmObject()

class SectionTranslationItemAPI(
    val language: String,
    val name: String
)

open class SectionTranslationItem(
    var language: String = "",
    var name: String = ""
) : RealmObject()

class SubsectionAPI(
    val subsections: ArrayList<SubsectionItemAPI>
)

open class Subsection(
    var subsections: RealmList<SubsectionItem> = RealmList<SubsectionItem>()
) : RealmObject()

class SubsectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SubsectionTranslationItemAPI>
)

open class SubsectionItem(
    var code: String = "",
    var color: String = "",
    var translations: RealmList<SubsectionTranslationItem> = RealmList<SubsectionTranslationItem>()
) : RealmObject()

class SubsectionTranslationItemAPI(
    val language: String,
    val name: String
)

open class SubsectionTranslationItem(
    var language: String = "",
    var name: String = ""
) : RealmObject()

class WordAPI(
    val words: ArrayList<WordItemAPI>
)

open class Word(
    var words: RealmList<WordItem> = RealmList<WordItem>()
) : RealmObject()

class WordItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationItemAPI>,
    val close_senses: ArrayList<CloseSenseItemAPI>
)

open class WordItem(
    var code: String = "",
    var image: String? = null,
    var translations: RealmList<WordTranslationItem> = RealmList<WordTranslationItem>(),
    var close_senses: RealmList<CloseSenseItem> = RealmList<CloseSenseItem>()
) : RealmObject()

class WordTranslationItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymItemAPI>
)

open class WordTranslationItem(
    var language: String = "",
    var name: String = "",
    var definition: String = "",
    var comment: String = "",
    var image_description: String = "",
    var synonyms: RealmList<SynonymItem> = RealmList<SynonymItem>()
) : RealmObject()

class SynonymItemAPI(
    val synonym: String
)

open class SynonymItem(
    var synonym: String = ""
) : RealmObject()

class CloseSenseItemAPI(
    val close_sense: String
)

open class CloseSenseItem(
    var close_sense: String = ""
) : RealmObject()