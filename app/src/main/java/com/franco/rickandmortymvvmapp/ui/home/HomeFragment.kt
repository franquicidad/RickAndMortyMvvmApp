package com.franco.rickandmortymvvmapp.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.databinding.FragmentHomeBinding
import com.franco.rickandmortymvvmapp.lastVisibleEvents
import com.franco.rickandmortymvvmapp.ui.PagingAdapter
import com.franco.rickandmortymvvmapp.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class HomeFragment(
    ) : Fragment(R.layout.fragment_home) {

    private  val homeViewModel: HomeViewModel by viewModels()
     var list = emptyList<Character>()
     private var lastPosition:Int = 0
    private lateinit var binding:FragmentHomeBinding

    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

         binding = FragmentHomeBinding.inflate(layoutInflater).apply {

           val pagingAdapter = PagingAdapter(lifecycleScope)
            recyclerCharacter.adapter=pagingAdapter
             pagingAdapter.stateRestorationPolicy=RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

             lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
            lifecycleScope.collectFlow(homeViewModel.spinner){
                progress.visible = it
            }
            lifecycleScope.collectFlow(homeViewModel.characters){
                pagingAdapter.submitList(it)
            }
            lifecycleScope.collectFlow(recyclerCharacter.lastVisibleEvents){
                homeViewModel.notifyLastVisible(it)
            }
            val  layoutManager = recyclerCharacter.layoutManager as GridLayoutManager

            recyclerCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    homeViewModel.notifyLastVisible(layoutManager.findLastVisibleItemPosition())
                    lastPosition = layoutManager.findLastVisibleItemPosition()
                    Log.i("Position", "Position---->  $lastPosition")

                }

            })

        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}