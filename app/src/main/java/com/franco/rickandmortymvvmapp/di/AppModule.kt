package com.franco.rickandmortymvvmapp.di

import com.franco.rickandmortymvvmapp.data.database.LocalDataSource
import com.franco.rickandmortymvvmapp.data.domain.Repository
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSource
import com.franco.rickandmortymvvmapp.ui.home.HomeFragment
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return Repository(localDataSource, remoteDataSource)
    }

    @Provides
    fun providesRepoToVm(repository: Repository): HomeViewModel {
        return HomeViewModel(repository)
    }

//    @Provides
//    fun providesHomeVmToFragment(repository: Repository,localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource):HomeFragment{
//        return HomeFragment(repository, localDataSource, remoteDataSource)
//    }
//}
}