package com.aamir.ashraf.pokemon.feature_pokemon.data.repository

import android.util.Log
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.PokeMonDao
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.ApiInterface
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDto
import com.aamir.ashraf.pokemon.feature_pokemon.domain.repository.PokeMonRepository
import com.aamir.ashraf.pokemon.utils.Resource
import com.aamir.ashraf.pokemon.utils.safeApiCall

class PokeMonRepositoryImpl(
    private val api:ApiInterface,
    private val dao:PokeMonDao
):PokeMonRepository {
    override suspend fun getPokeMons(): Resource<PokeMonDto> {
        val localData = dao.getListOfPokeMons()
        if(localData.results.isNullOrEmpty()){
            //means first time login
            Log.e("aamir","local data $localData")
            return Resource.Success(
               localData.toPokeMonDto()
            )
        }
        else{
            val response = safeApiCall{api.getAllPokeMon()}
            if(response is Resource.Success){
                response.data?.let { pokeMonDto ->
                    Log.e("aamir",pokeMonDto.toString())
                val entity = pokeMonDto.toPokeMonEntity()
                dao.insertPokeMons(entity)
                    val localData = dao.getListOfPokeMons()
                    Log.e("aamir",localData.toString())

                }
            }
            return response
        }
    }

    override suspend fun getPokeMonDetails(id: Int): Resource<PokeMonDetailsEntity> {
        TODO("Not yet implemented")
    }
}