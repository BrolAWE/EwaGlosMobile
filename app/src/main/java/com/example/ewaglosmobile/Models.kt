package com.example.ewaglosmobile

class SectionAPI(
    val sections: ArrayList<SectionItemAPI>
)

class SectionTranslationItemAPI(
    val language: String,
    val name: String
)

class SectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SectionTranslationItemAPI>
)

class SubsectionAPI(
    val subsections: ArrayList<SubsectionItemAPI>
)

class SubsectionTranslationItemAPI(
    val language: String,
    val name: String
)

class SubsectionItemAPI(
    val code: String,
    val color: String,
    val translations: ArrayList<SubsectionTranslationItemAPI>
)

class WordsAPI(
    val words: ArrayList<WordItemAPI>
)


class SynonymItemAPI(
    val synonym: String
)

class CloseSenseItemAPI(
    val close_sense: String
)


class WordTranslationItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymItemAPI>
)

class WordItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationItemAPI>,
    val close_senses: ArrayList<CloseSenseItemAPI>
)

class WordAPI(
    val word: ArrayList<WordsItemAPI>
)

class SynonymsItemAPI(
    val synonym: String
)

class CloseSensesItemAPI(
    val close_sense: String
)


class WordTranslationsItemAPI(
    val language: String,
    val name: String,
    val definition: String,
    val comment: String,
    val image_description: String,
    val synonyms: ArrayList<SynonymsItemAPI>
)

class WordsItemAPI(
    val code: String,
    val image: String,
    val translations: ArrayList<WordTranslationsItemAPI>,
    val close_senses: ArrayList<CloseSensesItemAPI>
)