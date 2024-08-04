package com.aamir.ashraf.pokemon.feature_pokemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonDetailsEntity
import com.aamir.ashraf.pokemon.feature_pokemon.data.local.entity.PokeMonEntity

@Database(
    entities = [PokeMonDetailsEntity::class,PokeMonEntity::class],
    version = 1
)
@TypeConverters(PokeMonConverters::class)
abstract class PokeMonDataBase: RoomDatabase() {
    abstract val dao:PokeMonDao
}