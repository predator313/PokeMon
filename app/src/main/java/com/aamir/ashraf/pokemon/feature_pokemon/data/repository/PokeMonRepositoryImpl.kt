package com.aamir.ashraf.pokemon.feature_pokemon.data.repository

import android.util.Log
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.PokeMonDao
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonDetailDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toPokeMonEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.ApiInterface
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDetailsDto
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
        if(localData.results.isEmpty()){
            //means first time login

            val response = safeApiCall{api.getAllPokeMon()}
            if(response is Resource.Success){
                response.data?.let { pokeMonDto ->
                    Log.e("aamir",pokeMonDto.toString())
                    val entity = pokeMonDto.toPokeMonEntity()
                    dao.insertPokeMons(entity)


                }
            }
            return response
        }
        else{
            Log.e("aamir","local data $localData")
            return Resource.Success(
                localData.toPokeMonDto()
            )
        }
    }

    override suspend fun getPokeMonDetails(id: Int): Resource<PokeMonDetailsDto> {
        val localData = dao.getPokeMonDetails(id)
        if(localData==null || localData.abilities.isEmpty()){
            //first time so need to make network call
            val response = safeApiCall { api.getPokeMonDetailById(id) }
            Log.e("james",response.toString())
            if(response is Resource.Success){
                response.data?.let { pokeMonDetailDto->
                    Log.e("james",pokeMonDetailDto.toString())
                    val entity = pokeMonDetailDto.toPokeMonDetailsEntity()
                    dao.insertPokeMonDetails(entity)
                }
            }
            return response
        }

        return Resource.Success(
            localData.toPokeMonDetailDto()
        )



    }
}