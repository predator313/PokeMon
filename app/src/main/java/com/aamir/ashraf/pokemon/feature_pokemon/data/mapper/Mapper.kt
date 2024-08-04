package com.aamir.ashraf.pokemon.feature_pokemon.data.mapper

import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMon
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDetailsDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDto
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.MainScreenModel

fun PokeMonEntity.toPokeMonDto():PokeMonDto{
    return PokeMonDto(
        results = results
    )
}
fun PokeMonDto.toPokeMonEntity():PokeMonEntity{
    return PokeMonEntity(
        results = results
    )
}

fun PokeMonDetailsEntity.toPokeMonDetailDto():PokeMonDetailsDto{
    return PokeMonDetailsDto(
        id = id,
        baseExperience = baseExperience,
        isDefault = isDefault,
        height = height,
        name = name,
        order = order,
        weight = weight,
        abilities = abilities
    )
}
fun PokeMonDetailsDto.toPokeMonDetailsEntity():PokeMonDetailsEntity{
    return PokeMonDetailsEntity(
        id = id,
        baseExperience = baseExperience,
        isDefault = isDefault,
        height = height,
        name = name,
        order = order,
        weight = weight,
        abilities = abilities

    )

}
fun PokeMon.toMainScreenModel():MainScreenModel{
    return MainScreenModel(
        name = name,
        url = url
    )
}