package com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case

import com.aamir.ashraf.pokemon.feature_pokemon.domain.repository.PokeMonRepository

class GetAllPokeMon(
    private val pokeMonRepository: PokeMonRepository
) {
    suspend operator fun invoke() = pokeMonRepository.getPokeMons()
}