package com.franco.rickandmortymvvmapp.ui.navHostFragment

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainNavHostFragment :NavHostFragment() {

//    @Inject
//    lateinit var fragmentFactory: CustomFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        childFragmentManager.fragmentFactory = fragmentFactory
    }
}