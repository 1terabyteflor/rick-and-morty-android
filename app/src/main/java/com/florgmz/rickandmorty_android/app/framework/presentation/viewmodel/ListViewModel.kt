package com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel

import androidx.lifecycle.*
import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.Resource
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.usecase.GetCharactersListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel(private val getCharactersListUseCase: GetCharactersListUseCase): ViewModel() {
    private val _charactersLiveData = MutableLiveData<CharactersResponse?>()
    val charactersLiveData: LiveData<CharactersResponse?> = _charactersLiveData

    fun getCharacters() {
        viewModelScope.launch {
            val response = getCharactersListUseCase.call()
            _charactersLiveData.postValue(response)
        }
    }
    class ListViewModelFactory(private val getCharactersListUseCase: GetCharactersListUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetCharactersListUseCase::class.java).newInstance(getCharactersListUseCase)
        }
    }

}