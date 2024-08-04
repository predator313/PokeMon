package com.aamir.ashraf.pokemon.feature_pokemon.data.remote

import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDetailsDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/v2/pokemon")
    suspend fun getAllPokeMon():Response<PokeMonDto>

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokeMonDetailById(@Path("id") pokeMonId:Int):Response<PokeMonDetailsDto>
}