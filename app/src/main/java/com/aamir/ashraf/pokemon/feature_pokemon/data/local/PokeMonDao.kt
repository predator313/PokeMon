package com.aamir.ashraf.pokemon.feature_pokemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonEntity

@Dao
interface PokeMonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokeMons(pokeMonEntity: PokeMonEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokeMonDetails(pokeMonDetailsEntity: PokeMonDetailsEntity)

    @Query("select * from PokeMonEntity")
    suspend fun getListOfPokeMons():PokeMonEntity
    @Query("select * from PokeMonDetailsEntity where id = :id")
    suspend fun getPokeMonDetails(id:Int):PokeMonDetailsEntity

}