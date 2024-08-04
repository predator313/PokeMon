package com.aamir.ashraf.pokemon.feature_pokemon.domain.model

import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.AbilityFeature
import com.google.gson.annotations.SerializedName

data class DetailScreenModel(
    val id:Int,

    val baseExperience:Int,
    val height:Int,
    val isDefault:Boolean,
    val name:String,
    val order:Int,
    val weight:Int,
    val abilities:List<AbilityFeature>
)
