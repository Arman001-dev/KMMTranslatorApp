package com.example.kmmtranslatorapp.android.di

import android.app.Application
import com.example.kmmtranslatorapp.database.TranslateDatabase
import com.example.kmmtranslatorapp.translate.data.history.SqlDelightHistoryDataSource
import com.example.kmmtranslatorapp.translate.data.local.DatabaseDriverFactory
import com.example.kmmtranslatorapp.translate.data.remote.HttpClientFactory
import com.example.kmmtranslatorapp.translate.data.translate.KtorTranslateClient
import com.example.kmmtranslatorapp.translate.domain.history.HistoryDataSource
import com.example.kmmtranslatorapp.translate.domain.translate.TranslateClient
import com.example.kmmtranslatorapp.translate.domain.translate.TranslateUseCase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClientClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriverDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase.invoke(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(client: TranslateClient, dataSource: HistoryDataSource): TranslateUseCase {
        return TranslateUseCase(client, dataSource)
    }
}