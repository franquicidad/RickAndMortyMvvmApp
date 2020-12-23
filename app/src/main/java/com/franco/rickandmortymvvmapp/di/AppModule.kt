package com.franco.rickandmortymvvmapp.di

import android.content.Context
import androidx.room.Room
import com.franco.rickandmortymvvmapp.BASE_URL
import com.franco.rickandmortymvvmapp.DATABASE_NAME
import com.franco.rickandmortymvvmapp.data.database.CharactersDatabase
import com.franco.rickandmortymvvmapp.data.database.LocalDataSource
import com.franco.rickandmortymvvmapp.data.database.LocalDataSourceImpl
import com.franco.rickandmortymvvmapp.data.domain.Repository
import com.franco.rickandmortymvvmapp.data.domain.RepositoryImpl
import com.franco.rickandmortymvvmapp.data.network.RMService
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSource
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSourceImpl
import com.franco.rickandmortymvvmapp.data.network.RickAndMortyApi
import com.franco.rickandmortymvvmapp.ui.detail.DetailMortyViewModel
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideDatabase(
            @ApplicationContext  app:Context
    )
     = Room.databaseBuilder(
    app,
    CharactersDatabase::class.java,
    DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(db:CharactersDatabase)= db.CharacterDAO()

    @Singleton
    @Provides
    fun provideRetrofitService() :RMService{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(RMService::class.java)
                  }


//  @Singleton
//    @Provides
//    fun provideRemoteDataSource():RemoteDataSource{
//        return RemoteDataSourceImpl()
//    }

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSourceImpl,
        remoteDataSource: RemoteDataSourceImpl
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providesRepoToVm(repositoryImpl: RepositoryImpl): HomeViewModel {
        return HomeViewModel(repositoryImpl)
    }

    @Singleton
    @Provides
    fun provideDetailViewModel(repositoryImpl: RepositoryImpl):DetailMortyViewModel{
        return  DetailMortyViewModel(repositoryImpl)
    }


//    @Singleton
//    @Provides
//    fun providesLocalDatasource(localDataSource: LocalDataSource):LocalDataSource{
//        return localDataSource
//    }


//    @Provides
//    fun providesHomeVmToFragment(repository: Repository,localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource):HomeFragment{
//        return HomeFragment(repository, localDataSource, remoteDataSource)
//    }
//}
}