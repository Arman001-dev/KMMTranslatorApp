package com.example.kmmtranslatorapp.translate.domain.translate

import com.example.kmmtranslatorapp.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}