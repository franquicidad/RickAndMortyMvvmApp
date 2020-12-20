package com.franco.rickandmortymvvmapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franco.rickandmortymvvmapp.R

class DetailMortyFragment : Fragment(R.layout.detail_morty_fragment) {

    companion object {
        fun newInstance() = DetailMortyFragment()
    }

    private lateinit var viewModel: DetailMortyViewModel



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailMortyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}