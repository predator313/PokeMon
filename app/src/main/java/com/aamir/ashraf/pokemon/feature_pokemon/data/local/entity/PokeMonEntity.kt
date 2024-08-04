package com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMon
@Entity
data class PokeMonEntity(
    val results:List<PokeMon>,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
)