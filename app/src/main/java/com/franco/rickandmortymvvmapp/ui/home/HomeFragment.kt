package com.franco.rickandmortymvvmapp.ui.home


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.*
import com.franco.rickandmortymvvmapp.databinding.FragmentHomeBinding
import com.franco.rickandmortymvvmapp.ui.PagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class HomeFragment  : Fragment(R.layout.fragment_home) {

    private  val homeViewModel: HomeViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val pagingAdapter =PagingAdapter(lifecycleScope)
        binding.apply {
            recyclerCharacter.apply {
                adapter= pagingAdapter
                pagingAdapter.stateRestorationPolicy=RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                layoutManager =GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)

                lifecycleScope.apply {
                    collectFlow(recyclerCharacter.lastVisibleEvents){
                        homeViewModel.notifyLastVisible(it)
                    }

                    collectFlow(homeViewModel.spinner){
                        progress.visible =it
                    }

                }

                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        homeViewModel.notifyLastVisible((layoutManager as GridLayoutManager).findLastVisibleItemPosition())

                    }
                })
            }
        }

        homeViewModel.characterQuery.observe(viewLifecycleOwner, Observer {
            pagingAdapter.submitList(it)
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
       inflater.inflate(R.menu.menu_gallery,menu)

        val searchItem =menu.findItem(R.id.action_search)
        val searchView =searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            //update search query
            homeViewModel.searchQuery.value = it
        }
    }
}