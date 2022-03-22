package com.florgmz.rickandmorty_android.core.di

import com.florgmz.rickandmorty_android.app.framework.source.CharactersRemoteSource
import com.florgmz.rickandmorty_android.app.framework.source.CharactersRemoteSourceImpl
import com.florgmz.rickandmorty_android.core.data.repository.CharactersRepository
import com.florgmz.rickandmorty_android.core.data.repository.CharactersRepositoryImpl
import com.florgmz.rickandmorty_android.core.service.ApiClient
import com.florgmz.rickandmorty_android.core.service.ApiService
import com.florgmz.rickandmorty_android.core.usecase.GetCharactersListUseCase
import com.florgmz.rickandmorty_android.core.usecase.GetCharactersListUseCaseImpl
import com.florgmz.rickandmorty_android.core.usecase.GetSingleCharacterUseCase
import com.florgmz.rickandmorty_android.core.usecase.GetSingleCharacterUseCaseImpl
import com.florgmz.rickandmorty_android.utils.Constants.BASE_URL
import org.koin.dsl.module

val appModule = module {
    single {
        ApiClient.Builder<ApiService>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiService::class.java)
            .build()
    }

    single<CharactersRepository> {
        CharactersRepositoryImpl(get() as CharactersRemoteSource)
    }

    single<CharactersRemoteSource> { CharactersRemoteSourceImpl(get() as ApiService) }
    single<GetCharactersListUseCase> { GetCharactersListUseCaseImpl(get() as CharactersRepository)}
    single<GetSingleCharacterUseCase> { GetSingleCharacterUseCaseImpl(get() as CharactersRepository)}
}