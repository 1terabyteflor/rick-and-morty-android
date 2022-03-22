package com.florgmz.rickandmorty_android.core.data.repository

import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter

interface CharactersRepository {
    suspend fun getCharactersList(page: String) : CharactersResponse
    suspend fun getCharacterById(id: String) : SingleCharacter
}