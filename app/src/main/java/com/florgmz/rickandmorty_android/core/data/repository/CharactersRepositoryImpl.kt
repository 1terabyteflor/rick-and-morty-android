package com.florgmz.rickandmorty_android.core.data.repository

import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.app.framework.source.CharactersRemoteSource

class CharactersRepositoryImpl(private val charactersRemoteSource: CharactersRemoteSource)
    : CharactersRepository {
    override suspend fun getCharactersList(): CharactersResponse {
        return charactersRemoteSource.getCharactersList().data!!
    }

    override suspend fun getCharacterById(id: String): SingleCharacter {
        return charactersRemoteSource.getCharacterById(id).data!!
    }
}