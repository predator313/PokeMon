package com.aamir.ashraf.pokemon.feature_pokemon.data.local

import androidx.room.TypeConverter
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.AbilityFeature
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokeMonConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromAbilityList(abilities: List<AbilityFeature>): String = gson.toJson(abilities)

    @TypeConverter
    fun toAbilityList(abilitiesString: String): List<AbilityFeature> {
        val type = object : TypeToken<List<AbilityFeature>>() {}.type
        return gson.fromJson(abilitiesString, type)
    }

    @TypeConverter
    fun fromPokeMonList(pokeMons: List<PokeMon>): String = gson.toJson(pokeMons)

    @TypeConverter
    fun toPokeMonList(pokeMonListString: String): List<PokeMon> {
        val type = object : TypeToken<List<PokeMon>>() {}.type
        return gson.fromJson(pokeMonListString, type)
    }
}
