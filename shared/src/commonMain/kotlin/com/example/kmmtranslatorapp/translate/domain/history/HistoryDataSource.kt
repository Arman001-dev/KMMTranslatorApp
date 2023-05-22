package com.example.kmmtranslatorapp.translate.domain.history

import com.example.kmmtranslatorapp.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}