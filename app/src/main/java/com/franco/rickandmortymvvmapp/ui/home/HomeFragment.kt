package com.franco.rickandmortymvvmapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.data.database.LocalDataSource
import com.franco.rickandmortymvvmapp.data.domain.Repository
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSource
import com.franco.rickandmortymvvmapp.databinding.FragmentHomeBinding
import com.franco.rickandmortymvvmapp.lastVisibleEvents
import com.franco.rickandmortymvvmapp.ui.RickMortyCharacterAdapter
import com.franco.rickandmortymvvmapp.ui.dashboard.DashboardViewModel
import com.franco.rickandmortymvvmapp.ui.viewModelFactory
import com.franco.rickandmortymvvmapp.visible

class HomeFragment(
    private val repository: Repository,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ) : Fragment() {

    private lateinit var factory: viewModelFactory
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


//        homeViewModel =
//                ViewModelProvider(this).get(HomeViewModel::class.java)


        val binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            // attach everything related to viewBinding
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel

            val adapter =RickMortyCharacterAdapter(lifecycleScope)

            lifecycleScope.collectFlow(homeViewModel.spinner){
                progress.visible = it
            }
            lifecycleScope.collectFlow(homeViewModel.characters){
                adapter.submitList(it)
            }
            lifecycleScope.collectFlow(recyclerCharacter.lastVisibleEvents){
                homeViewModel.notifyLastVisible(it)
            }

            recyclerCharacter.adapter = adapter

            val layoutManager = recyclerCharacter.layoutManager as GridLayoutManager

            recyclerCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    homeViewModel.notifyLastVisible(layoutManager.findLastVisibleItemPosition())
                }
            })

        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val repository =Repository(localDataSource,remoteDataSource)
        factory = viewModelFactory(repository)
        homeViewModel =  ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        homeViewModel.characters
    }
}