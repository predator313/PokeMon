package com.aamir.ashraf.pokemon.feature_pokemon.domain.repository

import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDetailsDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDto
import com.aamir.ashraf.pokemon.utils.Resource

interface PokeMonRepository {
    suspend fun getPokeMons():Resource<PokeMonDto>
    suspend fun getPokeMonDetails(id:Int):Resource<PokeMonDetailsDto>
}