//package com.franco.rickandmortymvvmapp.ui
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentFactory
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.franco.rickandmortymvvmapp.data.database.LocalDataSource
//import com.franco.rickandmortymvvmapp.data.domain.Repository
//import com.franco.rickandmortymvvmapp.data.network.RemoteDataSource
//import com.franco.rickandmortymvvmapp.ui.home.HomeFragment
//import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel
//import javax.inject.Inject
//
////@Suppress("UNCHECKED_CAST")
////class viewModelFactory(private val repository: Repository):ViewModelProvider.NewInstanceFactory() {
////
////    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
////        return HomeViewModel(repository) as T
////
////    }
////}
//
//class CustomFragmentFactory
//@Inject
//constructor(
//    private val repository: Repository,
//    private val localDataSource: LocalDataSource,
//    private val remoteDataSource: RemoteDataSource
//):FragmentFactory(){
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        return when(className){
//            HomeFragment::class.java.name ->{
//                HomeFragment(repository,localDataSource,remoteDataSource)
//            }
//            else -> super.instantiate(classLoader, className)
//        }
//
//    }
//}