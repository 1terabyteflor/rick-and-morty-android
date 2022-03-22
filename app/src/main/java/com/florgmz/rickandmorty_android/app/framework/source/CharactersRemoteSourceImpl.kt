package com.florgmz.rickandmorty_android.app.framework.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.service.ApiService

class CharactersRemoteSourceImpl(private val service: ApiService): BaseDataSource(), CharactersRemoteSource {
    override suspend fun getCharactersList() = getResult { service.getCharactersList() }
    override suspend fun getCharacterById(id: String) = getResult { service.getCharactersById(id) }
}