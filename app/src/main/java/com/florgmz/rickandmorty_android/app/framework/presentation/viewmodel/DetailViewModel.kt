package com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel

import androidx.lifecycle.*
import com.florgmz.rickandmorty_android.app.framework.model.CharactersResponse
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.usecase.GetSingleCharacterUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val getSingleCharacterUseCase: GetSingleCharacterUseCase): ViewModel() {
    private val _characterDetailLiveData = MutableLiveData<SingleCharacter?>()
    val characterDetailLiveData: LiveData<SingleCharacter?> = _characterDetailLiveData

    fun getCharacter(id: Long) {
        viewModelScope.launch {
            val response = getSingleCharacterUseCase.call(id)
            _characterDetailLiveData.postValue(response)
        }
    }

    class DetailViewModelFactory(private val getSingleCharacterUseCase: GetSingleCharacterUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetSingleCharacterUseCase::class.java).newInstance(getSingleCharacterUseCase)
        }
    }

}