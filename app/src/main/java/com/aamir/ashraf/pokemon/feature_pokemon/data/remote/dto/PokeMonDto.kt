package com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokeMonDto(
    @SerializedName("results")
    val results:List<PokeMon>

)
data class PokeMon(
    val name:String,
    val url:String
)
