package com.franco.rickandmortymvvmapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.domain.Repository
import kotlinx.coroutines.flow.Flow

class DetailMortyViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel() {

    val characters : Flow<List<Character>> get() = repository.getCharactersRepo()

}