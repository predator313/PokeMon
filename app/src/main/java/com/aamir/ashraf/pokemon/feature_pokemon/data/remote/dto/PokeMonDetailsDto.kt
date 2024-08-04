package com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokeMonDetailsDto(
    val id:Int,
    @SerializedName("base_experience")
    val baseExperience:Int,
    val height:Int,
    @SerializedName("is_default")
    val isDefault:Boolean,
    val name:String,
    val order:Int,
    val weight:Int,
    val abilities:List<AbilityFeature>


)
data class AbilityFeature(
    val slot:Int,
    @SerializedName("is_hidden")
    val isHidden:Boolean,
    val ability: Ability

)
data class Ability(
    val name:String,


)
