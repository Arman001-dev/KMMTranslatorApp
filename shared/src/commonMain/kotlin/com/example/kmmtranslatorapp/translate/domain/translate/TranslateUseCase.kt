package com.example.kmmtranslatorapp.translate.domain.translate

import com.example.kmmtranslatorapp.core.domain.language.Language
import com.example.kmmtranslatorapp.core.domain.util.Resource
import com.example.kmmtranslatorapp.translate.domain.history.HistoryDataSource
import com.example.kmmtranslatorapp.translate.domain.history.HistoryItem

class TranslateUseCase(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {

    suspend fun execute(fromLanguage: Language, fromText: String, toLanguage: Language): Resource<String> {
        return try {
            val translatedText = client.translate(fromLanguage, fromText, toLanguage)
            historyDataSource.insertHistoryItem(
                HistoryItem(null, fromLanguage.langCode, fromText, toLanguage.langCode, translatedText)
            )
            Resource.Success(translatedText)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}