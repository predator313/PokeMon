package com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case

import com.aamir.ashraf.pokemon.feature_pokemon.domain.repository.PokeMonRepository

class GetPokeMonDetail(
    private val repository: PokeMonRepository
) {
    suspend operator fun invoke(id:Int) = repository.getPokeMonDetails(id)
}