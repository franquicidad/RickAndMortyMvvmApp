package com.franco.rickandmortymvvmapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _spinner= MutableStateFlow(true)
    val spinner : StateFlow <Boolean> get()=_spinner

    val characters :Flow<List<Character>> get() = repository.getCharactersRepo()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

     fun notifyLastVisible(lastVisible: Int) {
            viewModelScope.launch {
                repository.checkRequireNewPage(lastVisible)
                _spinner.value = false
            }
    }


}