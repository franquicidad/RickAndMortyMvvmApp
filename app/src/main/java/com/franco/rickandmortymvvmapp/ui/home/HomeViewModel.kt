package com.franco.rickandmortymvvmapp.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.domain.Repository
import com.franco.rickandmortymvvmapp.data.domain.RepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class HomeViewModel @ViewModelInject constructor(
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