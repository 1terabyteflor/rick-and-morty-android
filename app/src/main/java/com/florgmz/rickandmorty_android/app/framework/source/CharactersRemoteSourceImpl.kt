package com.florgmz.rickandmorty_android.app.framework.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.service.ApiService

class CharactersRemoteSourceImpl(private val service: ApiService): CharactersRemoteSource {
    override suspend fun getCharactersList(page: String): MutableLiveData<CharactersResponse> {
        val responseLiveData = MutableLiveData<CharactersResponse>()
        try {
            val response = service.getCharactersList(page)
            if(response.isSuccessful) {
                response.body()?.let { list ->
                    responseLiveData.value = list
                }
            } else {
                Log.e("error:", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
        return responseLiveData
    }

    override suspend fun getCharacterById(id: String): MutableLiveData<SingleCharacter> {
        val responseByIdLiveData = MutableLiveData<SingleCharacter>()
        try {
            val response = service.getCharactersById(id)
            if(response.isSuccessful) {
                response.body()?.let {
                    responseByIdLiveData.value = it
                }
            } else {
                Log.e("error:", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
        return responseByIdLiveData
    }
}