package com.aamir.ashraf.pokemon.di

import android.app.Application
import androidx.room.Room
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.PokeMonDao
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.PokeMonDataBase
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.ApiInterface
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.RetrofitInstance
import com.aamir.ashraf.pokemon.feature_pokemon.data.repository.PokeMonRepositoryImpl
import com.aamir.ashraf.pokemon.feature_pokemon.domain.repository.PokeMonRepository
import com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case.GetAllPokeMon
import com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case.GetPokeMonDetail
import com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case.GetPokeMonUseCase
import com.aamir.ashraf.pokemon.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokeMonModule {
    //local dependencies
    @Singleton
    @Provides
    fun providePokeMonDataBase(context:Application):PokeMonDataBase{
        return Room
            .databaseBuilder(context,
                PokeMonDataBase::class.java,
                DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun providePokeMonDao(db:PokeMonDataBase):PokeMonDao{
        return db.dao
    }

    //remote dependencies
    @Singleton
    @Provides
    fun provideApiInterface():ApiInterface{
        return RetrofitInstance.api
    }
    @Singleton
    @Provides
    fun providePokeMonRepository(apiInterface: ApiInterface,dao: PokeMonDao):PokeMonRepository{
        return PokeMonRepositoryImpl(apiInterface,dao)
    }
    @Singleton
    @Provides
    fun providePokeMonUseCase(pokeMonRepository: PokeMonRepository):GetPokeMonUseCase{
        return GetPokeMonUseCase(getAllPokeMon = GetAllPokeMon(pokeMonRepository),
            getPokeMonDetail = GetPokeMonDetail(pokeMonRepository)
        )

    }

}