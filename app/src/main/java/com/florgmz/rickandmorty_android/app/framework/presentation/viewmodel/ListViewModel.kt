package com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel

import androidx.lifecycle.*
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.usecase.GetCharactersListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel(private val getCharactersListUseCase: GetCharactersListUseCase): ViewModel() {
    private val listLiveData: MutableLiveData<List<SingleCharacter?>> by lazy {
        MutableLiveData<List<SingleCharacter?>>().also {
            getCharactersList()
        }
    }

    fun getCharactersList() {
        viewModelScope.launch {
            listData(getCharactersListUseCase.call("1").results)
        }
    }

    fun listData(result: List<SingleCharacter?>) {
        listLiveData.postValue(result)
    }

    fun getCharactersLiveData(): LiveData<List<SingleCharacter?>> {
        return listLiveData
    }

    class ListViewModelFactory(private val getCharactersListUseCase: GetCharactersListUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetCharactersListUseCase::class.java).newInstance(getCharactersListUseCase)
        }
    }

}