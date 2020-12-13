package com.franco.rickandmortymvvmapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.franco.rickandmortymvvmapp.data.domain.Repository
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class viewModelFactory(private val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
        return HomeViewModel(repository) as T
    }
}