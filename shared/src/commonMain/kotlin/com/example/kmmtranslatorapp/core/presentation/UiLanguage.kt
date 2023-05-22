package com.example.kmmtranslatorapp.core.presentation

import com.example.kmmtranslatorapp.core.domain.language.Language

expect class UiLanguage {
    val language: Language

    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}