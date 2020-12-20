package com.franco.rickandmortymvvmapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.databinding.FragmentHomeBinding
import com.franco.rickandmortymvvmapp.lastVisibleEvents
import com.franco.rickandmortymvvmapp.ui.RickAdapter
import com.franco.rickandmortymvvmapp.ui.RickMortyCharacterAdapter

import com.franco.rickandmortymvvmapp.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(
    ) : Fragment(R.layout.fragment_home) {


    private  val homeViewModel: HomeViewModel by viewModels()
    lateinit var adapter: RickAdapter
     var list = emptyList<Character>()
    lateinit var layoutManager:GridLayoutManager


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


//        homeViewModel =
//                ViewModelProvider(this).get(HomeViewModel::class.java)

        adapter = RickAdapter(list)
        val binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            // attach everything related to viewBinding
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel


            lifecycleScope.collectFlow(homeViewModel.spinner){
                progress.visible = it
            }
            lifecycleScope.collectFlow(homeViewModel.characters){

                layoutManager = recyclerCharacter.layoutManager as GridLayoutManager

                adapter = RickAdapter(it)
                recyclerCharacter.layoutManager =layoutManager
                recyclerCharacter.adapter = adapter


                //adapter.submitList(it)
            }
            lifecycleScope.collectFlow(recyclerCharacter.lastVisibleEvents){
                homeViewModel.notifyLastVisible(it)
            }

            recyclerCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    homeViewModel.notifyLastVisible(layoutManager.findLastVisibleItemPosition())
                }
            })

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.characters
    }
}