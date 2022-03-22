package com.florgmz.rickandmorty_android.app.framework.model

data class CharactersResponse (
    val info: Info,
    val results: List<SingleCharacter>
)