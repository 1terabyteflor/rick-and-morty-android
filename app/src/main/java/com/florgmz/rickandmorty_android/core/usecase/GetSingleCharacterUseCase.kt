package com.florgmz.rickandmorty_android.core.usecase

import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.data.repository.CharactersRepository

interface GetSingleCharacterUseCase {
    suspend fun call(id: Long): SingleCharacter
}

class GetSingleCharacterUseCaseImpl(private val repository: CharactersRepository): GetSingleCharacterUseCase {
    override suspend fun call(id: Long): SingleCharacter {
        return repository.getCharacterById(id.toString())
    }
}