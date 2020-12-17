package com.franco.rickandmortymvvmapp.di

import com.franco.rickandmortymvvmapp.data.database.LocalDataSource
import com.franco.rickandmortymvvmapp.data.domain.RepositoryImpl
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSource
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): RepositoryImpl {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providesRepoToVm(repositoryImpl: RepositoryImpl): HomeViewModel {
        return HomeViewModel(repositoryImpl)
    }

//    @Provides
//    fun providesHomeVmToFragment(repository: Repository,localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource):HomeFragment{
//        return HomeFragment(repository, localDataSource, remoteDataSource)
//    }
//}
}