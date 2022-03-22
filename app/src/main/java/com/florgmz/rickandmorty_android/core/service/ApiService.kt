package com.florgmz.rickandmorty_android.core.service

import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    suspend fun getCharactersList(
        @Query("page") page: String
    ): Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getCharactersById(
        @Path("id") id: String
    ): Response<SingleCharacter>
}