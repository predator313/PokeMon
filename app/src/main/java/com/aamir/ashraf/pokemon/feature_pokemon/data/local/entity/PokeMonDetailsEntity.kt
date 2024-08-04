package com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.AbilityFeature
import com.google.gson.annotations.SerializedName
@Entity
data class PokeMonDetailsEntity(
    @PrimaryKey
    val id:Int,
    @SerializedName("base_experience")
    val baseExperience:Int,
    val height:Int,
    @SerializedName("is_default")
    val isDefault:Boolean,
    val name:String,
    val order:Int,
    val weight:Int,
    val abilities:List<AbilityFeature>,

)
