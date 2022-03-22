package com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel

import androidx.lifecycle.*
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.usecase.GetSingleCharacterUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val getSingleCharacterUseCase: GetSingleCharacterUseCase): ViewModel() {
    private val listData: MutableLiveData<SingleCharacter> by lazy {
        MutableLiveData<SingleCharacter>()
    }

    private fun setListData(result: SingleCharacter) {
        listData.postValue(result)
    }

    fun getCharacter(id: Long) {
        viewModelScope.launch {
            setListData(getSingleCharacterUseCase.call(id))
        }
    }

    fun getCharacterLiveData(): LiveData<SingleCharacter> {
        return listData
    }

    class DetailViewModelFactory(private val getSingleCharacterUseCase: GetSingleCharacterUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetSingleCharacterUseCase::class.java).newInstance(getSingleCharacterUseCase)
        }
    }

}